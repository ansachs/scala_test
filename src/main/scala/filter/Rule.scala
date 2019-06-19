package filter

import dataclasses.{Clothing, Person}

import scala.util.{Failure, Success, Try}

abstract class Rule {
  def applyRule(tryPerson: Try[Person], order: Int): Try[Person]
}

trait Condition {
  def apply(person: Person, order: Int = -1): Boolean
}

object RuleBuilder {
  def generate(failureCondition: Condition): Rule = {

    class SpecificRule extends Rule {
      override def applyRule(tryPerson: Try[Person],
                             order: Int) =
          tryPerson.flatMap( person => {
            if (failureCondition(person, order)) Failure(new Exception("fail")) else Success(person)
          })
    }
    new SpecificRule
  }
}