package dataclasses

import org.scalatest.{BeforeAndAfter, FunSpec}
import PersonImplicits._


class PersonTest extends FunSpec with BeforeAndAfter {
  val person = Person()
  val defaultClothing = Clothing(pajamas = Some(1))
  val defaultPerson: Person = person.copy(clothing = defaultClothing)

  describe("person implicits") {
    it("getAndAddOne adds one to clothing items") {
      val personWithSocks = person.copy(clothing = Clothing(socks = Some(1)))

      personWithSocks.getAndAddOne(socksLens)
      assert(personWithSocks.clothing.socks.contains(1))
    }

    it("apply action applies designated action to the person") {
      val personWithFootwear = person.copy().applyAction(1)

      assert(personWithFootwear.clothing.footwear.contains(1))
    }

    it("count greater than one returns true if an item count is greater than one") {
      val invalidPerson = person.copy()
        .applyAction(1)
        .applyAction(1)

      assert(invalidPerson.countGreaterThanOne)
    }
  }
}