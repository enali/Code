package example.scala.leetcode

object StringToInteger {
  def myAtoi(str: String): Int = {
    var tmp = str.trim  // 去掉前后的空白字符
    if (tmp.isEmpty) return 0
    val neg = if (tmp(0) == '-') -1 else 1  // 保留正负号
    tmp = if (tmp(0) == '+' || tmp(0) == '-') tmp.substring(1) else tmp

    var left = 0
    while (left < tmp.length && tmp(left) == '0') left += 1  // 消除前缀0
    var right = left
    while (right < tmp.length && tmp(right) >= '0' && tmp(right) <= '9') right += 1  // 定位第1个非数字字符
    tmp = tmp.substring(left, right)
    if (tmp.isEmpty) return 0  // 即字串全0构成

    if (tmp.length > 10 || (tmp.length == 10 && tmp(0) > '2')) { // 数字超过10位, 必然溢出
      if (neg == -1) return Int.MinValue
      else return Int.MaxValue
    }

    var sum = 0
    var factor = neg  // 恢复正负, 主要的恶心点在于Int.MinValue是没办法表示成正数
    tmp.reverse.foreach{ c =>
      sum += (c-'0') * factor
      factor *= 10
    }
    if (neg > 0 ^ sum > 0) {
      if (neg == -1) return Int.MinValue
      else return Int.MaxValue
    } else return sum  // 最后的溢出判断
  }

  def main(args: Array[String]): Unit = {
    val t = Vector("", "+1", "-1", "   ", "   +1", "   -0012a42",
      "2147483647", "-2147483648", "12345678991", "2147483648", "-2147483648")
    val ans = Vector(0, 1, -1, 0, 1, -12, Int.MaxValue, Int.MinValue, Int.MaxValue, Int.MaxValue, Int.MinValue)
    t.zip(ans) foreach { case (s, a) =>
      assert(myAtoi(s) == a)
    }
  }
}
