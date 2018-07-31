package example.scala.leetcode

object ReverseInteger {
  def reverse(x: Int): Int = {
    if (x == 0 || x == Int.MinValue) return 0  // 整数的最小值不得不特殊处理, 因为其不能求绝对值
    var count = 0
    val ary = Array.fill(10)(0)
    var n = math.abs(x)
    while (n / 10 != 0) {
      ary(count) = n % 10
      count += 1
      n /= 10
    }
    ary(count) = n

    if (count == 10 && ary(0) > 2) return 0  // 整数的最大值最高位为2, 因此大于2的一定溢出

    var left = 0
    while (ary(left) == 0) left += 1
    var sum = 0
    var product = 1
    for (i <- count to left by -1) {
      sum += ary(i) * product
      product *= 10
    }
    sum = if (x < 0) -1 * sum else sum  // 调整正负
    if (x > 0 ^ sum > 0) 0 else sum  // 判断溢出
  }

  def main(args: Array[String]): Unit = {
    val t = Int.MaxValue - 5
    println(reverse(t))
  }
}
