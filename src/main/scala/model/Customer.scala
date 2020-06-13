
package model

import java.time.LocalDate

// Must be at least 13 to shop and meet age restrictions for Items
case class Customer(firstName: String, lastName: String, dateOfBirth: LocalDate, address: Address)

case class Address(houseNumberOrName: String, firstLine: String, secondLine: Option[String], city: String, county: Option[String], postcode: String)

