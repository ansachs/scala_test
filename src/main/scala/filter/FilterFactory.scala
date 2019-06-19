package filter

import dataclasses.{COLD, Clothing, HOT, Person, Temperature}
import output.{ColdResponse, HotResponse}

class FilterFactory {
  def build(temp: Temperature): MainFilter = {

    val responses = temp match {
      case HOT => new HotResponse
      case COLD => new ColdResponse
      case _ => throw new Exception("temperature not matched in responses")
    }

    val filters = temp match {
      case HOT => Filters.warmWeatherFilters
      case COLD => Filters.coldWeatherFilters
      case _ => throw new Exception("temperature not matched in filters")
    }

    val person = Person(clothing = Clothing(pajamas = Some(1)))

    new MainFilter(filters, responses, person)
  }

}
