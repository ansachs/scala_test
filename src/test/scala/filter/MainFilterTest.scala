package filter

import dataclasses.{Clothing, Person}
import org.scalatest.{BeforeAndAfter, FunSpec}
import output.ColdResponse
import dataclasses.PersonImplicits._

import scala.util.Failure

class MainFilterTest extends FunSpec with BeforeAndAfter {
  val testFilters: List[Rule] = List(Filters.takeOfPjsFirst, Filters.socksBeforeFootwear, Filters.coolWeatherDressed)
  val defaultClothing = Clothing(pajamas = Some(1))
  val defaultTestPerson = Person(clothing = defaultClothing)
  val mainFilter = new MainFilter(testFilters, new ColdResponse, defaultTestPerson)

  describe("application of rules in filter") {
    it("applys take of PJs rule to person correctly") {
      val person = defaultTestPerson.copy().applyAction(3)
      val result = mainFilter.checkRules(person, 0)
      assert(result.isInstanceOf[Failure[Person]])
    }

    it("applys socks rule correctly") {
      val clothing = defaultClothing.copy(pajamas = Some(0))
      val person = defaultTestPerson.copy(clothing).applyAction(1)
      val result = mainFilter.checkRules(person, 3)
      assert(result.isInstanceOf[Failure[Person]])
    }
  }

}
