package validator.instances

import cats.implicits._
import error.ValidationError
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import validator.OrderFixtures
import validator.Validator._

class AddressValidatorSpec extends AnyFlatSpec with Matchers with OrderFixtures {

  "validate" must "Invalid if not valid UK postcode" in {
    val address = validAddress.copy(postcode = "postcode")
    val expected = ValidationError("customer.address.postcode", "Must be a valid UK postcode").invalidNec

    val actual = address.validate

    actual mustBe expected
  }

  it must "Valid if valid UK postcode" in {
    val address = validAddress.copy(postcode = "N6 6EB")
    val expected = address.valid

    val actual = address.validate

    actual mustBe expected
  }

}
