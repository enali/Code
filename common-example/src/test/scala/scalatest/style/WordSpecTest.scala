package scalatest.style

import org.scalatest.WordSpec

/**
  * WordSpec, 适用于熟悉specs2(scala的BDD测试框架)的团队
  */
class WordSpecTest extends WordSpec {
  "A Set" when {
    "empty" should {
      "have size 0" in {
        assert(Set.empty.isEmpty)
      }
      "produce NoSuchElementException when head is invoked" in {
        assertThrows[NoSuchElementException] {
          Set.empty.head
        }
      }
    }
  }
}
