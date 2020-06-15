package validator.instances

import cats.implicits._
import cats.data.ValidatedNec
import error.ValidationError
import model.Item
import validator.Validator
import validator.Validator._

object ItemValidator extends Validator[Item] {

  override def validate(item: Item): ValidatedNec[ValidationError, Item] = {
    (
      item.uuid.valid,
      nonEmptyString(item.name)("item.name"),
      validatePositiveInteger(item.weightKg)("item.weightKg"),
      validatePositiveInteger(item.cost)("item.cost"),
      item.ageRestriction.valid
    ).mapN(Item)
  }

  private def validatePositiveInteger(i: Int)(fieldName: String): ValidatedNec[ValidationError, Int] =
    if (i > 0) i.valid
    else ValidationError(fieldName, "must be a positive integer").invalidNec

}
