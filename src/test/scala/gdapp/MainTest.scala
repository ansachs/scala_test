package gdapp
import org.scalatest._
import output.{ConsoleOutput, Output}

class MainTest extends FunSpec with BeforeAndAfter {
  val main: Main.type = Main
  main.console = new ConsoleOutput with MockMyConsole

  val out: MockMyConsole = main.console.asInstanceOf[MockMyConsole]

  val badArgs: Array[String] = "1, 2, 3".split(",")
  val goodArgs: Array[String] = "HOT 1, 2, 3".split(",")
  val goodArgs2: Array[String] = "COLD 1, 2, 3".split(",")

  before {
    out.messages = Seq()
    out.command = ""
  }

  it ("will fail with no temp argument") {
    assertThrows[Exception](Main.main(badArgs))

    assert(out.messages.equals(Seq("fail")))
    assert(out.command.equals("exit"))
  }


}

trait MockMyConsole extends Output{
  var messages: Seq[String] = Seq()
  var command: String = ""

  override def out(s: String): Unit = {
    messages = messages.+:(s)
  }

  override def exit(): Unit = {
    command = "exit"
  }
}
