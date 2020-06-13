package validator

import cats.data.ValidatedNec
import error.ValidationError
import model._

trait Validator[V] {
  def validate(v: V): ValidatedNec[ValidationError, V]
}


object Validator {

  def apply[V](implicit validator: Validator[V]): Validator[V] = validator

  def validate[V: Validator](v: V): ValidatedNec[ValidationError, V] = Validator[V].validate(v)

  implicit val orderValidator: Validator[Order] = OrderValidator.validator

  implicit class ValidatorOps[V](val v: V) {
    def validate(implicit validator: Validator[V]): ValidatedNec[ValidationError, V] =
      validator.validate(v)
  }

}
