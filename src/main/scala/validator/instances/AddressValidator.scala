package validator.instances

import cats.data.ValidatedNec
import cats.implicits._
import error.ValidationError
import model.Address
import validator.Validator
import validator.Validator.{nonEmptyString, regexValidator}

object AddressValidator extends Validator[Address] {

  def validate(address: Address): ValidatedNec[ValidationError, Address] = {
    (
      nonEmptyString(address.houseNumberOrName)("customer.address.houseNumberOrName"),
      nonEmptyString(address.firstLine)("customer.address.firstLine"),
      address.secondLine.valid,
      nonEmptyString(address.city)("customer.address.city"),
      address.county.valid,
      validatePostcode(address.postcode)
      ).mapN(Address)
  }

  def validatePostcode(postcode: String): ValidatedNec[ValidationError, String] = {
    val regex = """^([a-zA-Z]{1,2}[0-9]{1,2}[a-zA-Z]{0,1}[\s]{0,1}[0-9]{1}[a-zA-Z]{2})$""".r
    regexValidator(postcode, regex, "customer.address.postcode", "Must be a valid UK postcode")
  }

}
