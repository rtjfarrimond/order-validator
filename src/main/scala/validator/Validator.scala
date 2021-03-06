package validator

import cats.data.ValidatedNec
import cats.syntax.validated._
import error.ValidationError
import model._
import validator.instances._

import scala.util.matching.Regex

trait Validator[V] {
  def validate(v: V): ValidatedNec[ValidationError, V]
}

object Validator {

  implicit class ValidatorOps[V](val v: V) {
    def validate(implicit validator: Validator[V]): ValidatedNec[ValidationError, V] =
      validator.validate(v)
  }

  implicit val orderValidator: Validator[Order] = OrderValidator
  implicit val customerValidator: Validator[Customer] = CustomerValidator
  implicit val addressValidator: Validator[Address] = AddressValidator
  implicit val itemValidator: Validator[Item] = ItemValidator
  implicit val paymentDetailsValidator: Validator[PaymentDetails] = PaymentDetailsValidator

  def nonEmptyString(s: String)(fieldName: String): ValidatedNec[ValidationError, String] =
    if (s == "")
      ValidationError(fieldName, "Must not be the empty String").invalidNec
    else
      s.valid

  def regexValidator(string: String, regex: Regex, id: String, errorMessage: String): ValidatedNec[ValidationError, String] =
    string match {
      case regex(_*) => string.valid
      case _         => ValidationError(id, errorMessage).invalidNec
    }

}
