package example.scala.leetcode

object ContainerWithMostWater {
  /**
    * O(n**2)
    * 最笨的办法, 2次循环
    * @param height
    * @return
    */
  def maxArea1(height: Array[Int]): Int = {
    var max = 0
    val len = height.length
    for (i <- 0 until len-1; j <- i+1 until len) {
      val area = (j-i) * math.min(height(i), height(j))
      if (area > max) max = area
    }
    max
  }

  /**
    * O(n)
    * 选最左/最右两条线开始, 如从(1, 6)开始,
    * 若1比6短, 则(1, 2), (1, 3), (1, 4), (1, 5)都将比(1, 6)小, 从(2, 6)开始继续找
    * 若1比6长, 则(2, 6), (3, 6), (4, 6), (5, 6)都将比(1, 6)小, 从(1, 5)开始继续找
    * @param height
    * @return
    */
  def maxArea2(height: Array[Int]): Int = {
    var max = 0
    val len = height.length
    for (i <- 0 until len-1; j <- i+1 until len) {
      val area = (j-i) * math.min(height(i), height(j))
      if (area > max) max = area
    }
    max
  }

  // 方法别名
  val maxArea = maxArea2 _

  def main(args: Array[String]): Unit = {
    val input = Vector(Array(1, 1), Array(1, 2))
    val output = Vector(1, 1)
    input.zip(output).foreach{
      case (in, out) => assert(maxArea(in) == out)
    }
  }
}
