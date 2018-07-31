package example.scala.leetcode

object RomanToInteger {
  val roman2int = Map(
    ('M', 1000), ('D', 500), ('C', 100), ('L', 50), ('X', 10), ('V', 5), ('I', 1), (' ', 0)
  )

  def romanToInt(s: String): Int = {
    var sum = 0
    var idx = 0
    val tmp = s + ' '
    while (idx < tmp.length-1) {
      val cur = roman2int(tmp(idx))
      val suf = roman2int(tmp(idx+1))
      if (cur < suf) {
        sum += suf - cur
        idx += 2
      } else {
        sum += cur
        idx += 1
      }
    }
    sum
  }

  def main(args: Array[String]): Unit = {
    val input = Vector("I", "IV", "IX", "X", "MMMCMXCIX")
    val output = Vector(1, 4, 9, 10, 3999)
    input.zip(output).foreach { case (in, out) =>
      assert(romanToInt(in) == out)
    }
  }
}
