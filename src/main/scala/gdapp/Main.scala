package gdapp

import dataclasses.{COLD, HOT, Temperature}
import filter.FilterFactory
import output.{ConsoleOutput, Output}

object Main {
  var console: Output = new ConsoleOutput
  var filterFactory = new FilterFactory

  def main(args: Array[String]): Unit = {

    val temp: Option[Temperature] = args(0) match {
      case "HOT" => Some(HOT)
      case "COLD" => Some(COLD)
      case _ => None
    }

    if (temp.isEmpty) {
      console.out("fail")
      console.exit()
    }

    val filter = filterFactory.build(temp.get)
    filter.setIOInterface(console)
    filter.checkInputs(args.drop(1))
  }
}
