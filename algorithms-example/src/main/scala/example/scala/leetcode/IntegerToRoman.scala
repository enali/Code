package example.scala.leetcode

import scala.annotation.tailrec

object IntegerToRoman {
  val int2roman = Map(
    (0, ""),
    (1, "I"), (2, "II"), (3, "III"), (4, "IV"), (5, "V"), (6, "VI"), (7, "VII"), (8, "VIII"), (9, "IX"),
    (10, "X"), (20, "XX"), (30, "XXX"), (40, "XL"), (50, "L"), (60, "LX"), (70, "LXX"), (80, "LXXX"), (90, "XC"),
    (100, "C"), (200, "CC"), (300, "CCC"), (400, "CD"), (500, "D"), (600, "DC"), (700, "DCC"), (800, "DCCC"), (900, "CM"),
    (1000, "M"), (2000, "MM"), (3000, "MMM")
  )

  val ary = Array(Array("I", "V"), Array("X", "L"), Array("C", "D"), Array("M"))

  /**
    * 将整数转化为罗马数字
    * 重复数次：一个罗马数字重复几次，就表示这个数的几倍。
    * 右加左减：
    *   * 在较大的罗马数字的右边记上较小的罗马数字，表示大数字加小数字。
    *   * 在较大的罗马数字的左边记上较小的罗马数字，表示大数字减小数字。
    *   * 左减的数字有限制，仅限于I、X、C。比如45不可以写成VL，只能是XLV
    *   * 但是，左减时不可跨越一个位值。比如，99不可以用IC（ {\displaystyle 100-1} 100-1）表示，而是用XCIX（ {\displaystyle [100-10]+[10-1]} [100-10]+[10-1]）表示。（等同于阿拉伯数字每位数字分别表示。）
    *   * 左减数字必须为一位，比如8写成VIII，而非IIX。
    *   * 右加数字不可连续超过三位，比如14写成XIV，而非XIIII。（见下方“数码限制”一项。）
    * 加线乘千：
    *   * 在罗马数字的上方加上一条横线或者加上下标的Ⅿ，表示将这个数乘以1000，即是原数的1000倍。
    *   * 同理，如果上方有两条横线，即是原数的1000000（ {\displaystyle 1000^{2}} 1000^{{2}}）倍。
    * 数码限制：
    *   * 同一数码最多只能连续出现三次，如40不可表示为XXXX，而要表示为XL。
    * @param num
    * @return
    */
  def intToRoman1(num: Int): String = {
    var n = num
    var factor = 1
    var roman = ""
    while (n > 0) {
      roman = int2roman(n % 10 * factor) + roman
      n /= 10
      factor *= 10
    }
    roman
  }

  /**
    * 采用递归函数, 避免可变量的使用
    * @param num
    * @return
    */
  def intToRoman2(num: Int): String = {
    @tailrec
    def rst(n: Int, factor: Int, roman: String): String = {
      if (n == 0) roman
      else rst(n/10, factor*10, int2roman(n%10*factor) + roman)
    }
    rst(num, 1, "")
  }

  def intToRoman3(num: Int): String = {
    @tailrec
    def rst(n: Int, power: Int, roman: String): String = {
      if (n == 0) roman
      else rst(n/10, power+1, getRoman(n%10, power)+roman)
    }
    rst(num, 0, "")
  }

  /**
    * 获得指定位上对应的Roman字串
    * 由罗马数字的规律可见, 任意基数上, 1-3, 5-8比较有规律, 而4,9则相对没有规律
    * @param n, 对应数位上的数字
    * @param power, 冥数, 如个位为0, 十位为1, 百位为2
    * @return
    */
  private def getRoman(n: Int, power: Int): String = n match {
    case 0 =>
      ""
    case 4 =>
      ary(power)(0) + ary(power)(1)
    case 9 =>
      ary(power)(0) + ary(power+1)(0)
    case _ =>
      ary(power)(n / 5) * (n % 5)
  }

  /** 用柯西化实现类似函数别名的效果 **/
  val intToRoman = intToRoman1 _

  def main(args: Array[String]): Unit = {
    val input = Array(1, 10, 304, 3999)
    val output = Array("I", "X", "CCCIV", "MMMCMXCIX")
    input.zip(output).foreach { case (in, out) =>
      assert(intToRoman(in) == out)
    }
  }
}
