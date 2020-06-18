package validator.instances

import cats.implicits._
import cats.data.ValidatedNec
import error.ValidationError
import model.Order
import validator.Validator
import validator.Validator._

object OrderValidator extends Validator[Order] {

  def validate(order: Order): ValidatedNec[ValidationError, Order] = {
    (
      customerValidator.validate(order.customer),
      order.items.traverse(_.validate),
      order.paymentDetails.validate
      ).mapN(Order)
  }

}
