package validator.instances

import cats.implicits._
import cats.data.{NonEmptyChain, Validated, ValidatedNec}
import error.ValidationError
import model.Order
import validator.Validator
import validator.Validator._

object OrderValidator extends Validator[Order] {

  def validate(order: Order): ValidatedNec[ValidationError, Order] = {
    (
      customerValidator.validate(order.customer),
      returnValid(order.items),
      returnValid(order.paymentDetails)
      ).mapN(Order)
  }

  private def returnValid[T](t: T): ValidatedNec[ValidationError, T] =
    Validated.valid[NonEmptyChain[ValidationError], T](t)

}
