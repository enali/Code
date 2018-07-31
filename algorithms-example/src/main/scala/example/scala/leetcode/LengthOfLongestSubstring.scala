package example.scala.leetcode

import scala.collection.mutable.{Set=>MSet}

/**
  * 整理的思路就是用两个指针, 右指针右移.
  * 用集合决断字符是否重复.
  * 难点在于, left指针的移动, 即出现重复字符, 得让left指向重复字符的下一位置
  */
object LengthOfLongestSubstring {
  def lengthOfLongestSubstring(s: String): Int = {
    var set = MSet.empty[Char]
    var left, right = 0  // 左右指针
    var max = 0
    val len = s.length
    var flag = true
    while (flag) {
      // 如果右指针指向的字符未包含在集合中, 则添加字符并右移
      while (right < len && !set.contains(s(right))) {
        set += s(right)
        right += 1
      }  // 停止条件为, 右指针到头, 或遇到重复了
      if (set.size > max) max = set.size  // 更新最大值
      if (right == len) {
        flag = false
      } else {
        left = s.indexOf(s(right), left) + 1  // left指向重复字符的下一位置
        set = MSet(s.substring(left, right):_*)  // 将left->right的字串存入集合
        if (len - left <= max) flag = false // 如果剩余字符数 <= max, 则没必要再找了
      }
    }
    max
  }

  def main(args: Array[String]): Unit = {
    val test = Vector("abcabcbb", "bbbbb", "pwwkew")
    val ans = Vector(3, 1, 3)
    test.zip(ans).foreach{ case (t, a) =>
        assert(lengthOfLongestSubstring(t) == a)
    }
  }
}
