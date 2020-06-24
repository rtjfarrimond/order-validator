package validator.instances

import java.time.{LocalDate, Period}

import cats.data.ValidatedNec
import cats.implicits._
import error.ValidationError
import model.{Customer, Item, Order}
import validator.Validator
import validator.Validator._

object OrderValidator extends Validator[Order] {

  def validate(order: Order): ValidatedNec[ValidationError, Order] = {
    (
      order.customer.validate,
      validateItems(order.customer, order.items),
      order.paymentDetails.validate
      ).mapN(Order)
  }

  private def validateItems(customer: Customer, items: List[Item]): ValidatedNec[ValidationError, List[Item]] = {
    (
      items.traverse(_.validate),
      items.traverse(validateAgeRestrictedProduct(customer, _))
    ) mapN {
      case (_, _) => items
    }
  }

  private def validateAgeRestrictedProduct(customer: Customer, item: Item): ValidatedNec[ValidationError, Item] = {
    item.ageRestriction.map { minAge =>
      lazy val now = LocalDate.now()
      lazy val yearsOld = Period.between(customer.dateOfBirth, now).getYears
      if (yearsOld < minAge)
        ValidationError(
          "item.ageRestriction",
          s"Must be at least $minAge years old to buy item ${item.uuid}"
        ).invalidNec
      else
        item.valid
    } getOrElse item.valid
  }


}
