package validator

import cats.data._
import cats.implicits._
import error.ValidationError
import model.Order

object OrderValidator {

  val validator: Validator[Order] = new Validator[Order] {
    def validate(order: Order): ValidatedNec[ValidationError, Order] = {
      (
        returnValid(order.customer),
        returnValid(order.items),
        returnValid(order.paymentDetails)
        ).mapN(Order)
    }
  }

  def returnValid[T](value: T): ValidatedNec[ValidationError, T] =
      Validated.valid[NonEmptyChain[ValidationError], T](value)

}
