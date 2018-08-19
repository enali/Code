package example.scala.leetcode

import scala.annotation.tailrec

object AddTwoNumbers {
  def addTwoNumbers(l1: ListNode[Int], l2: ListNode[Int]): ListNode[Int] = {
    // tail 指向前一个节点, 在函数中为tail添加节点
    @tailrec
    def add(tail: ListNode[Int], t1: ListNode[Int], t2: ListNode[Int], car: Int): Unit = (t1, t2) match {
      case (null, null) =>  // t1/t2都为空
        if (car == 1) tail.next = new ListNode[Int](car)
      case (null, _) =>  // t1为空
        val sum = t2.x + car
        tail.next = new ListNode[Int](sum % 10)
        add(tail.next, null, t2.next, sum / 10)
      case (_, null) =>  // t2为空
        val sum = t1.x + car
        tail.next = new ListNode[Int](sum % 10)
        add(tail.next, t1.next, null, sum / 10)
      case _ =>  // 都不空
        val sum = t1.x + t2.x + car
        tail.next = new ListNode[Int](sum % 10)
        add(tail.next, t1.next, t2.next, sum / 10)
    }
    val sum = l1.x + l2.x
    val head = new ListNode[Int](sum % 10)
    add(head, l1.next, l2.next, sum / 10)

    head
  }

  def main(args: Array[String]): Unit = {
    val l1 = new ListNode(2)
//    l1.next = new ListNode(4)
//    l1.next.next = new ListNode(3)
    val l2 = new ListNode(9)
    l2.next = new ListNode(9)
    l2.next.next = new ListNode(9)

    var ptr = addTwoNumbers(l1, l2)
    while (ptr.next != null) {
      printf("%d -> ", ptr.x)
      ptr = ptr.next
    }
    println(ptr.x)
  }
}
