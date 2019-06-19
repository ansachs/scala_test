package filter

import dataclasses.{Clothing, Person}
import dataclasses.PersonImplicits._
import output.{Output, Response}

import scala.util.{Failure, Success, Try}

class MainFilter(private val filters: List[Rule], private val responses: Response, private val person: Person) {

  var IO: Output = null

  def checkInputs(args: Array[String]): Unit = {
    var personCopy = person.copy()

    args.toList
      .map(_.stripSuffix(","))
      .flatMap(_.toIntOption)
      .zipWithIndex
      .takeWhile {case (action: Int, idx: Int) =>
        if (idx != 0) sendToOutput(", ")

        val testRuleOnPerson = personCopy.applyAction(action)

        checkRules(testRuleOnPerson, idx) match {
          case Failure(ex) => {
            sendToOutput(ex.getMessage)
            false
          }
          case Success(modifiedPerson) => {
            sendToOutput(responses.printResponse(action).orNull)
            personCopy = modifiedPerson
            true
          }
        }
    }
  }

  def checkRules(person: Person, idx: Int): Try[Person] = {
    filters
      .foldRight(Try(person))((rule, tryPerson) => rule.applyRule(tryPerson, idx))
  }

  private def sendToOutput(message: String): Unit = {
    if (IO != null) {
      IO.out(message)
    }
  }

  def setIOInterface(output: Output): Unit = {
    IO = output
  }

}
