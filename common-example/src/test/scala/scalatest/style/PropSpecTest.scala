package scalatest.style

import scala.collection.BitSet
import scala.collection.immutable.{HashSet, TreeSet}

import org.scalatest.{Matchers, PropSpec}
import org.scalatest.prop.TableDrivenPropertyChecks

/**
  * PropSpec, 适用于只想写属性检查的测试的团队, (scalacheck, 属性测试的框架)
  */
class PropSpecTest extends PropSpec with TableDrivenPropertyChecks with Matchers {
  // 实例矩阵
  val examples = Table(
    "Set",
    BitSet.empty,
    HashSet.empty[Int],
    TreeSet.empty[Int]
  )

  property("an empty Set should have size 0") {
    forAll(examples) { set =>
      set.size should be (0)
    }
  }

  property("invoking head on an empty set should produce NoSuchElementException") {
    forAll(examples) { set =>
      a [NoSuchElementException] should be thrownBy { set.head }
    }
  }
}
