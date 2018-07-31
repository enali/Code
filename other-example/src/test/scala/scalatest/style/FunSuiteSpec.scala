package scalatest.style

import org.scalatest.FunSuite


/**
  * FunSuite, 适用于熟悉XUnit的团队
  */
class FunSuiteSpec extends FunSuite {
  test("An empty Set should have size 0") {
    assert(Set.empty.size == 0)
  }

  test("Invoking head on an empty Set should produce NoSuchElementException") {
    assertThrows[NoSuchElementException] {
      Set.empty.head
    }
  }
}
