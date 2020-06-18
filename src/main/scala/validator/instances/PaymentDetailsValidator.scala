package validator.instances

import cats.data._
import cats.implicits._
import error.ValidationError
import model.PaymentDetails
import validator.Validator
import validator.Validator._

object PaymentDetailsValidator extends Validator[PaymentDetails] {

  def validate(
    paymentDetails: PaymentDetails
  ): ValidatedNec[ValidationError, PaymentDetails] = {
    (
      nonEmptyString(paymentDetails.accountHolder)("paymentDetails.accountHolder"),
      validateAccountNumber(paymentDetails.accountNumber),
      validateSortCode(paymentDetails.sortCode)
    ).mapN(PaymentDetails)
  }

  private def validateAccountNumber(
    accountNumber: String
  ): ValidatedNec[ValidationError, String] = {
    regexValidator(accountNumber, """^(\d{8})$""".r, "paymentDetails.accountNumber", "Must be 8 digits")
  }

  private def validateSortCode(
    sortCode: String
  ): ValidatedNec[ValidationError, String] = {
    regexValidator(sortCode, """^(\d{6})$""".r, "paymentDetails.sortCode", "Must be 6 digits")
  }

}
