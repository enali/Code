package scalatest.style

import org.scalatest.FlatSpec

/**
  * FlatSpec, 适用于从XUnit转向BDD(行为驱动开发)的团队, flat强调不嵌套
  */
class FlatSpecTest extends FlatSpec{
  "An empty Set" should "have size 0" in {
    assert(Set.empty.size == 0)
  }

  // it 表示上例中的"An empty Set"
  it should "produce NoSuchElementException when head is invoked" in {
    assertThrows[NoSuchElementException] {
      Set.empty.head
    }
  }
}
