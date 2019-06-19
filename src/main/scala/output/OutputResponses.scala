package output

abstract class Response {
  def printResponse(command: Int): Option[String]
}

class HotResponse extends Response {
  def printResponse(command: Int): Option[String] =
    command match {
      case 1 => Some("sandals")
      case 2 => Some("sunglasses")
      case 3 => None
      case 4 => Some("shirt")
      case 5 => None
      case 6 => Some("shorts")
      case 7 => Some("leaving house")
      case 8 => Some("Removing PJs")
      case _ => None
    }
}

class ColdResponse extends Response {
  def printResponse(command: Int): Option[String] =
    command match {
      case 1 => Some("boots")
      case 2 => Some("hat")
      case 3 => Some("socks")
      case 4 => Some("shirt")
      case 5 => Some("jacket")
      case 6 => Some("pants")
      case 7 => Some("leaving house")
      case 8 => Some("Removing PJs")
      case _ => None
    }
}