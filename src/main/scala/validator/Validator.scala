package validator

import cats.data.ValidatedNec
import cats.syntax.validated._
import error.ValidationError
import model.{Address, Customer, Order}
import validator.instances.{AddressValidator, CustomerValidator, OrderValidator}

import scala.util.matching.Regex

trait Validator[V] {
  def validate(v: V): ValidatedNec[ValidationError, V]
}

object Validator {

  def apply[V](implicit validator: Validator[V]): Validator[V] = validator

  def validate[V: Validator](v: V): ValidatedNec[ValidationError, V] = Validator[V].validate(v)

  implicit class ValidatorOps[V](val v: V) {
    def validate(implicit validator: Validator[V]): ValidatedNec[ValidationError, V] =
      validator.validate(v)
  }

  implicit val orderValidator: Validator[Order] = OrderValidator
  implicit val customerValidator: Validator[Customer] = CustomerValidator
  implicit val addressValidator: Validator[Address] = AddressValidator

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
