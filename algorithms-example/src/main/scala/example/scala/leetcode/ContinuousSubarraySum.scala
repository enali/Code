package example.scala.leetcode

import scala.collection.mutable.{Map=>MMap}

object ContinuousSubarraySum {
  /**
    * 利用了模的一个特性, 即若两个数对k的模相同, 则两数的差一定是k的整数倍
    * @param nums, 数组
    * @param k, 模
    * @return 是否有子序列的和是k的整数倍
    */
  def checkSubarraySum(nums: Array[Int], k: Int): Boolean = {
    val m = MMap((0, -1))
    var sum = 0
    for (i <- nums.indices) {
      sum += nums(i)
      if (k != 0) sum %= k
      if (m.contains(sum)) {
        if (i-m(sum) >= 2) return true
      } else m(sum) = i
    }
    false
  }

  def main(args: Array[String]): Unit = {
    val input = Vector(Array(23, 2, 4, 6, 7), Array(23, 2, 6, 4, 7), Array(0, 0), Array(0))
    val args = Vector(7, 7, 0, 0)
    val output = Vector(true, true, true, false)
    input.zip(args).zip(output).foreach{ case ((ary, k), ans) =>
        assert(checkSubarraySum(ary, k) == ans)
    }
  }
}
