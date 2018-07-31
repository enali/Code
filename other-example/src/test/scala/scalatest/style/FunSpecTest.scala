package scalatest.style

import org.scalatest.FunSpec

/**
  * FunSpec, 适用于熟悉Ruby's RSpec工具的团队, 喜欢BDD的团队, 可任意嵌套
  */
class FunSpecTest extends FunSpec{
  // 多层嵌套
  describe("A Set") {
    describe("when empty") {
      it("should have size 0") {
        assert(Set.empty.size == 0)
      }
      it("should produce NoSuchElementException when head is invoked") {
        assertThrows[NoSuchElementException] {
          Set.empty.head
        }
      }
    }
  }
}
