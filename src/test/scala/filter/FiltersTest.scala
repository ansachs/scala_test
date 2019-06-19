package filter

import dataclasses.{Clothing, Person}
import org.scalatest._
import dataclasses.PersonImplicits._

import scala.util.{Success}

class FiltersTest extends FunSpec with BeforeAndAfter {
  val defaultPerson = Person(clothing = Clothing(pajamas = Some(1)))

  val clothedPerson: Person = Person()
    .applyAction(1)
    .applyAction(2)
    .applyAction(3)
    .applyAction(4)
    .applyAction(5)
    .applyAction(6)
    .applyAction(8)

  val ruleBuilder: RuleBuilder.type = RuleBuilder

  describe("TakeOffPjs") {

    it("will return Failure if removing PJs is not the first action") {
      val order = 0
      val tryPerson = defaultPerson.applyAction(3)

      val result = Filters.takeOfPjsFirst.applyRule(Success(tryPerson), order)

      assert(result.isFailure)
    }

    it("will return Success if removing PJs is the first action") {
      val order = 0
      val tryPerson = defaultPerson.applyAction(8)

      val result = Filters.takeOfPjsFirst.applyRule(Success(tryPerson), order)

      assert(result.isSuccess)
    }
  }

}
