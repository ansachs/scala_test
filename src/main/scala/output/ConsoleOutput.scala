package output

class ConsoleOutput extends Output {
  def out(s: String): Unit = Console.print(s)
  def exit() = System.exit(0)
}

trait Output {
  def out(s: String): Unit
  def exit(): Unit
}
