package model

import java.time.LocalDate

case class Customer(firstName: String, lastName: String, dateOfBirth: LocalDate, address: Address)

case class Address(
                    houseNumberOrName: String,
                    firstLine: String,
                    secondLine: Option[String],
                    city: String,
                    county: Option[String],
                    postcode: String)

