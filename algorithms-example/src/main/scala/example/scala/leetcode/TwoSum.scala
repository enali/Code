package example.scala.leetcode

import scala.annotation.tailrec
import scala.collection.mutable.{Map=>MMap}

object TwoSum {
  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    val m = MMap.empty[Int, Int]
    val len = nums.length
    @tailrec
    def search(id: Int): Array[Int] = {
      if (id >= len) return Array(-1, -1)
      val sub = target - nums(id)
      if (m.contains(sub)) return Array(m(sub), id)
      else {
        m(nums(id)) = id
        search(id+1)
      }
    }
    search(0)
  }

  def main(args: Array[String]): Unit = {
    val nums = Array(2, 7, 11, 15)
    val rst = twoSum(nums, 9)
    rst.foreach(println)
  }
}
