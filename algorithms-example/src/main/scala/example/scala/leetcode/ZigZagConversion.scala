package example.scala.leetcode

object ZigZagConversion {
  def convert(s: String, numRows: Int): String = {
    if (s.isEmpty) return ""
    if (numRows == 1) return s
    val ary = Array.fill(numRows)("")
    val len = s.length
    var i = 0
    while (i < len) {
      for (k <- 0 until numRows if i < len) {  // 更新垂直列
        ary(k) += s(i)
        i += 1
      }
      for (k <- numRows-2 until 0 by -1 if i < len) {  // 更新斜列
        ary(k) += s(i)
        i += 1
      }
    }
    ary.reduce(_+_)
  }

  def main(args: Array[String]): Unit = {
    val s = "PAYPALISHIRING"
    print(convert(s, 3))
  }
}
