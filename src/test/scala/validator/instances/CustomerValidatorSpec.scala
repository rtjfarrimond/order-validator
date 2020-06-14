package validator.instances

import java.time.LocalDate

import cats.implicits._
import error.ValidationError
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import validator.OrderFixtures
import validator.Validator._

class CustomerValidatorSpec extends AnyFlatSpec with Matchers with OrderFixtures {

  "validate" must "Invalid if customer is less than 13 years old" in {
    val customer = validCustomer.copy(dateOfBirth = LocalDate.now())
    val expected = ValidationError("customer.dateOfBirth", "Must be at least 13 years old").invalidNec

    val actual = customer.validate

    actual mustBe expected
  }

  it must "Valid if customer is exactly 13 years old" in {
    val customer = validCustomer.copy(dateOfBirth = LocalDate.now().minusYears(13))
    val expected = customer.valid

    val actual = customer.validate

    actual mustBe expected
  }

  it must "Valid if customer is older than 13 years old" in {
    val customer = validCustomer.copy(dateOfBirth = LocalDate.now().minusYears(14))
    val expected = customer.valid

    val actual = customer.validate

    actual mustBe expected
  }

}
