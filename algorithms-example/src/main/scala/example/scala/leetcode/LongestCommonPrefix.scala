package example.scala.leetcode

object LongestCommonPrefix {
  /**
    * 求字串集合的公共子串
    * @param strs
    * @return
    */
  def longestCommonPrefix1(strs: Array[String]): String = {
    if (strs.isEmpty) return ""

    var pre = strs(0)
    for (i <- 1 until strs.length) {
      // 从上到下, 若pre不是公共子串, 则长度减1
      while (strs(i).indexOf(pre) != 0)
        pre = pre.substring(0, pre.length - 1)
    }
    pre
  }

  def main(args: Array[String]): Unit = {
    val input = Vector(Array.empty[String])
    val output = Vector("")
    input.zip(output).foreach { case (in, out) =>
      assert(longestCommonPrefix1(in) == out)
    }
  }
}
