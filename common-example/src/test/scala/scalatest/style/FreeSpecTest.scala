package scalatest.style

import org.scalatest.FreeSpec

/**
  * FreeSpec, 比较自由的Spec写法, 适用于有经验的BDD的团队, 且同意以此组织spec
  */
class FreeSpecTest extends FreeSpec {
  "A Set" - {
    "when empty" - {
      "should have size 0" in {
        assert(Set.empty.size == 0)
      }
      "should produce NoSuchElementException when head is invoked" in {
        assertThrows[NoSuchElementException] {
          Set.empty.head
        }
      }
    }
  }


}
