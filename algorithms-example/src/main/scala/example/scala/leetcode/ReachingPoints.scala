package example.scala.leetcode

import scala.collection.mutable.ArrayStack

class ReachingPoints {
  def reachingPoints(sx: Int, sy: Int, tx: Int, ty: Int): Boolean = {
    val stack = new ArrayStack[Int]()
    stack.push(0)
    var cur_x = sx
    var cur_y = sy
    while (!stack.isEmpty) {
      val k = stack.pop()

      val tmp_x = cur_x + (1-k) * cur_y
      val tmp_y = cur_y + k * cur_x

      if (tmp_x > tx || tmp_y > ty) {  // 越界回溯
        if (k == 0) stack.push(1)  // 此路不通, 换到1分支
        else {  // 已经是1分支了
          var t = stack.pop
          while (t == 1) {  // 连续跳过所有1分支
            cur_y -= cur_x
            if (stack.isEmpty) return false
            t = stack.pop
          }
          cur_x -= cur_y  // 第1个0分支
          stack.push(1)  // 换分支
        }
      } else if (tmp_x == tx && tmp_y == ty) {  // 到达终点
        return true
      } else {  // 下一步
        stack.push(k)
        stack.push(0)
        cur_x = tmp_x
        cur_y = tmp_y
      }
    }
    return false
  }
}

object ReachingPoints {
  def main(args: Array[String]): Unit = {
    val rp = new ReachingPoints
    println(rp.reachingPoints(1, 1, 2, 2))
  }
}
