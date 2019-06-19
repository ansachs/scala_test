package dataclasses

import scalaz.{Lens, LensFamily}
// import shapeless.Lens

case class Person(clothing: Clothing = Clothing(), isHome: Boolean = true)

case class Clothing(footwear: Option[Int] = None,
                    headwear: Option[Int] = None,
                    shirt: Option[Int] = None,
                    socks: Option[Int] = None,
                    jacket: Option[Int] = None,
                    pants: Option[Int] = None,
                    pajamas: Option[Int] = None
                 )

object PersonImplicits {

  type clothingLens = Lens[Person, Clothing]
  type clothingItemLens = Lens[Clothing, Option[Int]]

  val clothingLens: clothingLens =
    Lens.lensu[Person, Clothing]((a: Person, b: Clothing) => a.copy(clothing = b), _.clothing)

  val footwearLens: clothingItemLens =
    Lens.lensu[Clothing, Option[Int]]((a: Clothing, b: Option[Int]) => a.copy(footwear = b), _.footwear)
  val headwearLens: clothingItemLens =
    Lens.lensu[Clothing, Option[Int]]((a: Clothing, b: Option[Int]) => a.copy(headwear = b), _.headwear)
  val shirtLens: clothingItemLens =
    Lens.lensu[Clothing, Option[Int]]((a: Clothing, b: Option[Int]) => a.copy(shirt = b), _.shirt)
  val socksLens: clothingItemLens =
    Lens.lensu[Clothing, Option[Int]]((a: Clothing, b: Option[Int]) => a.copy(socks = b), _.socks)
  val jacketLens: clothingItemLens =
    Lens.lensu[Clothing, Option[Int]]((a: Clothing, b: Option[Int]) => a.copy(jacket = b), _.jacket)
  val pantsLens: clothingItemLens =
    Lens.lensu[Clothing, Option[Int]]((a: Clothing, b: Option[Int]) => a.copy(pants = b), _.pants)
  val pajamasLens: clothingItemLens =
    Lens.lensu[Clothing, Option[Int]]((a: Clothing, b: Option[Int]) => a.copy(pajamas = b), _.pajamas)

  def itemLens(itemLens: clothingItemLens): LensFamily[Person, Person, Option[Int], Option[Int]] = clothingLens andThen itemLens

  val itemLensList: List[clothingItemLens] = List(footwearLens, headwearLens, shirtLens, socksLens, jacketLens, pantsLens, pajamasLens)

  implicit class GetState(val person: Person) extends AnyVal {

    def getAndAddOne(lens: clothingItemLens) = {
      itemLens(lens).set(person, itemLens(lens).get(person).fold(Some(1))(a => Some(a + 1)))
    }

    def getAndSubtractOne(lens: clothingItemLens): Person = {
      val item = itemLens(lens).get(person)
      if (item.isDefined) itemLens(lens).set(person, Some(item.get - 1)) else person
    }

    def applyAction(actionNum: Int): Person = {
      actionNum match {
        case 1 => getAndAddOne(footwearLens)
        case 2 => getAndAddOne(headwearLens)
        case 3 => getAndAddOne(socksLens)
        case 4 => getAndAddOne(shirtLens)
        case 5 => getAndAddOne(jacketLens)
        case 6 => getAndAddOne(pantsLens)
        case 7 => person.copy(isHome = false)
        case 8 => getAndSubtractOne(pajamasLens)
      }
    }

    def getClothingFields: List[Option[Int]] = {
      itemLensList.map {
        item => item.get(person.clothing)
      }
    }

    def countGreaterThanOne: Boolean = {
      getClothingFields.exists(a => a.isDefined && a.get > 1)
    }
  }

}