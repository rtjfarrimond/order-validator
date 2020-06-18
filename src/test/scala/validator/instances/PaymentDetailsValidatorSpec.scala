package validator.instances

import cats.implicits._
import error.ValidationError
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import validator.OrderFixtures
import validator.Validator._

class PaymentDetailsValidatorSpec extends AnyFlatSpec with Matchers with OrderFixtures {

  "validate" must "Valid if account number is exactly 8 digits" in {
    val paymentDetails = validPaymentDetails.copy(accountNumber = "12345678")
    val expected = paymentDetails.valid

    val actual = paymentDetails.validate

    actual mustBe expected
  }

  it must "Invalid if account number is less than 8 digits" in {
    val paymentDetails = validPaymentDetails.copy(accountNumber = "1234567")
    val expected = ValidationError("paymentDetails.accountNumber", "Must be 8 digits").invalidNec

    val actual = paymentDetails.validate

    actual mustBe expected
  }

  it must "Invalid if account number is greater than 8 digits" in {
    val paymentDetails = validPaymentDetails.copy(accountNumber = "123456789")
    val expected = ValidationError("paymentDetails.accountNumber", "Must be 8 digits").invalidNec

    val actual = paymentDetails.validate

    actual mustBe expected
  }

  "validate" must "Valid if sort code is exactly 6 digits" in {
    val paymentDetails = validPaymentDetails.copy(sortCode = "123456")
    val expected = paymentDetails.valid

    val actual = paymentDetails.validate

    actual mustBe expected
  }

  it must "Invalid if sort code is less than 6 digits" in {
    val paymentDetails = validPaymentDetails.copy(sortCode = "12345")
    val expected = ValidationError("paymentDetails.sortCode", "Must be 6 digits").invalidNec

    val actual = paymentDetails.validate

    actual mustBe expected
  }

  it must "Invalid if sort code is greater than 6 digits" in {
    val paymentDetails = validPaymentDetails.copy(sortCode = "1234567")
    val expected = ValidationError("paymentDetails.sortCode", "Must be 6 digits").invalidNec

    val actual = paymentDetails.validate

    actual mustBe expected
  }

}
