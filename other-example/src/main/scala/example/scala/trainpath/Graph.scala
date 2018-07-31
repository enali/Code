package example.scala.trainpath

import scala.annotation.tailrec
import scala.collection.mutable.{ArrayStack => MArrayStack, Map => MMap, Set => MSet}
import scala.reflect.ClassTag
import scala.io

class Graph[T <: Node : ClassTag](nodes: Iterable[T]) {
  val items = MMap.empty[T, MSet[T]]
  val nodeByName = MMap.empty[String, T]
  nodes.foreach { node =>
    items.getOrElseUpdate(node, MSet.empty[T])
    nodeByName(node.id) = node
  }

  def this() {
    this(Array.empty[T])
  }

  def apply(id: String): String = {
    val node = nodeByName(id)
    node.id + ": " + items(node).mkString(" -> ")
  }

  def itemsDist: MMap[T, MMap[T, Double]] = items.map { case (k,v) =>
    (k, MMap.empty[T, Double] ++ v.map(n => (n, n.dist(k))))
  }

  def itemsSwitch: MMap[T, MMap[T, Int]] = items.map { case (k,v) =>
    (k, MMap.empty[T, Int] ++ v.map(n => (n, if (n.node_type==1) 1 else 0)))
  }

  // 获取所有节点
  def allnodes = items.keys

  def getNode(id: String) = nodeByName(id)

  def getNodeConnect(id: String) = items(getNode(id))

  // 添加节点
  def addNode(t: T) = if (!items.contains(t)) {
    items(t) = MSet.empty[T]
    nodeByName(t.id) = t
  }

  // 删除一个节点
  def delNode(t: T) = if (items.contains(t)) {
    items -= t
    items.foreach { case (k, v) => v -= t}
    nodeByName -= t.id
  }

  // 删除一个连接
  def delNodeConnect(from: T, to: T) = if (items.contains(from)) items(from) -= to

  // 添加双向边
  def addEdge(t1: T, t2: T) = {
    addDirectedEdge(t1, t2).addDirectedEdge(t2,t1)
  }

  // 添加单向边
  def addDirectedEdge(from: T, to: T) = {
    addNode(from)
    items(from).add(to)
    this
  }

  // true表示x增大；false表示x减小
  def getMinDist(direct: Boolean): MMap[T, MMap[T, Double]] = {
    val nodes = allnodes
    val directedItemsL = directedItems(itemsDist, direct)
    for {
      k <- nodes
      i <- nodes
      j <- nodes
    } {
      // 即i跟k连通，k跟j连通
      if (directedItemsL(i).contains(k) && directedItemsL(k).contains(j)) {
        val tmp = directedItemsL(i)(k) + directedItemsL(k)(j)
        if (directedItemsL(i).contains(j)) {  // 即i跟j连通
          if (tmp < directedItemsL(i)(j)) directedItemsL(i)(j) = tmp
        } else {
          directedItemsL(i)(j) = tmp
        }
      }
    }
    directedItemsL
  }

  /**
   * 获取道岔数最小的图的链表表示
   * @param direct
   * @return
   */
  def getMinSwitch(direct: Boolean): MMap[T, MMap[T, Int]] = {
    val nodes = allnodes
    val directedItemsL = directedItems(itemsSwitch, direct)
    for {
      k <- nodes
      i <- nodes
      j <- nodes
    } {
      // 即i跟k连通，k跟j连通
      if (directedItemsL(i).contains(k) && directedItemsL(k).contains(j)) {
        val tmp = directedItemsL(i)(k) + directedItemsL(k)(j)
        if (directedItemsL(i).contains(j)) {  // 即i跟j连通
          if (tmp < directedItemsL(i)(j)) directedItemsL(i)(j) = tmp
        } else {
          directedItemsL(i)(j) = tmp
        }
      }
    }
    directedItemsL
  }

  /**
   * 获取图的带方向性的链表表示
   * @param inplace 是否在参数原地修改
   */
  private def directedItems[A](itemsL: MMap[T, MMap[T, A]], direct: Boolean, inplace: Boolean = false): MMap[T, MMap[T, A]] = {
    val cloneItems = if (inplace) itemsL else itemsL.map{ case (k,v) => (k, v.clone) }
    if (direct) {
      cloneItems.map { case (k, v) =>
        (k, v.filter { case (k1, _) => k1.xloc >= k.xloc })
      }
    } else {
      cloneItems.map { case (k, v) =>
        (k, v.filter { case (k1, _) => k1.xloc <= k.xloc })
      }
    }
  }

  def path(from: T, to: T): Array[T] = {
    if (from.xloc == to.xloc && from.yloc == to.xloc) return Array.empty[T]

    val direct = if (from.xloc <= to.xloc) true else false
    val minDist = getMinDist(direct)  // 有向的，两节点最小距离
    val minSwitch = getMinSwitch(direct)  // 有向的， 两节点最少道岔数
    val distItems = directedItems(itemsDist, direct)  // 有向的， 两节点距离
    val switchItems = directedItems(itemsSwitch, direct)  // 有向的， 两节点道岔数

    val cloneItems = items.map{ case (k,v) => (k, v.clone) }
    val itemsL = if (direct) {  // 有向的， 节点连接图
      cloneItems.map { case (k, v) =>
        (k, v.filter { k1 => k1.xloc >= k.xloc })
      }
    } else {
      cloneItems.map { case (k, v) =>
        (k, v.filter { k1 => k1.xloc <= k.xloc })
      }
    }

    val stack = new MArrayStack[T]  // 保存探索轨迹的栈
    val stackIt = new MArrayStack[Iterator[T]]  // 保存当前的探索位置

    var curdist = 0.0  // 当前的长度
    var curswitch = 0  // 当前经过的道岔数
    var distbound = Double.MaxValue  // 长度上界
    var switchBound = Int.MaxValue  // 道岔数上界
    var bestpath = Array.empty[T]
    var bestlen = 0  // 最佳路径的节点数
    var mmindist = 0.0  // 最佳路径的长度
    var mminswitch = 0  // 最佳路径的道岔数

    stack.push(from)
    val it = itemsL(from).toIterator
    if (!it.hasNext) return Array.empty[T]
    stackIt.push(it)

    while (stack.nonEmpty) {
      val cur = stack.top
      val nextIt = stackIt.top

      @tailrec
      def findNode(node: T): Option[T] = {
        println(minDist(cur).contains(to))
        println(minSwitch(cur).contains(to))
        println(curdist + minDist(cur)(to))
        println(curswitch + minSwitch(cur)(to))
        if (minDist(cur).contains(to) &&
          minSwitch(cur).contains(to) &&
          curdist + minDist(cur)(to) < distbound &&
          curswitch + minSwitch(cur)(to) < switchBound) {
          Some(node)
        } else if (!nextIt.hasNext) {
          None
        } else {
          findNode(nextIt.next)
        }
      }

      val node = if (nextIt.hasNext) findNode(nextIt.next) else None
      if (node.isEmpty) {
        val tmp = stack.pop()
        stackIt.pop()
        if (stack.nonEmpty) {
          curdist -= distItems(stack.top)(tmp)
          curswitch -= switchItems(stack.top)(tmp)
        }
      } else {
        val nodeL = node.get
        curdist += distItems(cur)(nodeL)
        curswitch += switchItems(cur)(nodeL)
        stack.push(nodeL)
        stackIt.push(itemsL(nodeL).toIterator)

        if (nodeL == to) {
          if (curswitch < switchBound || (curswitch == switchBound && curdist <= distbound)) {
            bestpath = stack.toArray.reverse
            bestlen = stack.size
            mmindist = curdist
            mminswitch = curswitch
            switchBound = curswitch
            distbound = curdist
          }
          (1 to 2).foreach { _ =>
            val tmp = stack.pop()
            stackIt.pop()
            curdist -= distItems(stack.top)(tmp)
            curswitch -= switchItems(stack.top)(tmp)
          }
        }
      }
    }

    println(s"最短路径：$mmindist\t路径道岔数：$mminswitch\t节点数：$bestlen")
    println(s"路径：${bestpath.map(_.id).mkString("->")}")
    bestpath
  }
}

object Graph {
  val station_nodes = """d:\stations\station_nodes.lst"""
  val station_connects = """d:\stations\station_connects.lst"""
  val graph = createGraph(station_nodes, station_connects)

  def main(args: Array[String]): Unit = {
    val tmp = graph.path(graph.getNode("X1"), graph.getNode("X6"))
  }

  def createGraph(): Graph[StationNode] = createGraph(station_nodes, station_connects)

  def createGraph(nodes: String, connects: String): Graph[StationNode] = {
    val nameToStations = io.Source.fromFile(nodes).getLines().map(_.trim).filter(_.nonEmpty).map {line =>
      val Array(varname, name, xloc, yloc, node_type) = line.split(",")
      (varname, StationNode(varname, name, xloc.toDouble, yloc.toDouble, node_type.toInt))
    }.toMap
    val graph = new Graph[StationNode](nameToStations.values)  // 初始化graph
    io.Source.fromFile(connects).getLines().map(_.trim).filter(_.nonEmpty).map(_.split(",")).foreach { item =>
      val s1 = nameToStations(item(0))
      val s2 = nameToStations(item(1))
      graph.addEdge(s1, s2)
    }
    graph
  }

  def display1(direct: Boolean): Unit = {
    println(graph.getMinDist(direct).map{ case (k,v) =>
      s"${k.id} -> " + v.map{ case (k1, v1) =>
        s"${k1.id}(${v1.formatted("%.2f")})"
      }.mkString(" -> ")
    }.mkString("\n"))
  }

  def display2(direct: Boolean): Unit = {
    println(graph.getMinSwitch(direct).map{ case (k,v) =>
      s"${k.id} -> " + v.map{ case (k1, v1) =>
        s"${k1.id}(${v1.toInt})"
      }.mkString(" -> ")
    }.mkString("\n"))
  }
}
