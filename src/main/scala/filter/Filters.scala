package filter

import dataclasses.{Clothing, Person}
import dataclasses.PersonImplicits._


object Filters {

  val allClothing: Clothing =
  Clothing(Some(1), Some(1), Some(1), Some(1), Some(1), Some(1), Some(0))

  val warmWeatherDressed: Rule = RuleBuilder.generate(
    (person: Person, _: Int) => !person.isHome && !person.clothing.equals(
      allClothing.copy(socks = None, jacket = None)
    )
  )

  val coolWeatherDressed: Rule = RuleBuilder.generate(
    (person: Person, _: Int) => !person.isHome && !person.clothing.equals(allClothing)
  )

  val takeOfPjsFirst: Rule = RuleBuilder.generate(
    (person: Person, order: Int) =>
      order == 0 && person.clothing.pajamas.getOrElse(-1) != 0
  )

  val onePieceOnly: Rule = RuleBuilder.generate(
    (person: Person, _: Int) => person.countGreaterThanOne
  )

  val noSocksWhenHot: Rule = RuleBuilder.generate(
    (person: Person, _: Int) => person.clothing.socks.isDefined
  )

  val noJacketWhenHot: Rule = RuleBuilder.generate(
    (person: Person, _: Int) => person.clothing.jacket.isDefined
  )

  val pantsBeforeFootwear: Rule = RuleBuilder.generate(
    (person: Person, _: Int) => person.clothing.footwear.getOrElse(0) > 0 && person.clothing.pants.isEmpty
  )

  val socksBeforeFootwear: Rule = RuleBuilder.generate(
    (person: Person, _: Int) => person.clothing.footwear.getOrElse(0) > 0 && person.clothing.socks.isEmpty
  )

  val shirtBeforeHeadwearOrJacket: Rule = RuleBuilder.generate(
    (person: Person, _: Int) => ((person.clothing.headwear.getOrElse(0) > 0) && person.clothing.shirt.isEmpty) ||
      ((person.clothing.jacket.getOrElse(0) > 0) && person.clothing.shirt.isEmpty)
  )

  val warmWeatherFilters: List[Rule] =
    List(
      warmWeatherDressed, takeOfPjsFirst, onePieceOnly, noSocksWhenHot, noJacketWhenHot, pantsBeforeFootwear,
        shirtBeforeHeadwearOrJacket
    )

  val coldWeatherFilters: List[Rule] =
    List(
      coolWeatherDressed, takeOfPjsFirst, onePieceOnly, pantsBeforeFootwear, shirtBeforeHeadwearOrJacket,
      socksBeforeFootwear
    )


}

