package validator.instances

import cats.implicits._
import error.ValidationError
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import validator.OrderFixtures
import validator.Validator._

class ItemValidatorSpec extends AnyFlatSpec with Matchers with OrderFixtures {

  "validate" must "Invalid if weight is equal to zero" in {
    val items = validItem.copy(weightKg = 0) :: validItems
    val expected = ValidationError("item.weightKg", "must be a positive integer").invalidNec

    val actual = items.traverse(_.validate)

    actual mustBe expected
  }

  it must "Invalid if weight is less than zero" in {
    val items = validItem.copy(weightKg = -42) :: validItems
    val expected = ValidationError("item.weightKg", "must be a positive integer").invalidNec

    val actual = items.traverse(_.validate)

    actual mustBe expected
  }

  it must "Valid if weight is greater than zero" in {
    val value = 42
    val items = validItem.copy(weightKg = value) :: validItems
    val expected = items.valid

    val actual = items.traverse(_.validate)

    actual mustBe expected
  }

  it must "Invalid if cost is equal to zero" in {
    val items = validItem.copy(cost = 0) :: validItems
    val expected = ValidationError("item.cost", "must be a positive integer").invalidNec

    val actual = items.traverse(_.validate)

    actual mustBe expected
  }

  it must "Invalid if cost is less than zero" in {
    val items = validItem.copy(cost = -42) :: validItems
    val expected = ValidationError("item.cost", "must be a positive integer").invalidNec

    val actual = items.traverse(_.validate)

    actual mustBe expected
  }

  it must "Valid if cost is greater than zero" in {
    val value = 42
    val items = validItem.copy(cost = value) :: validItems
    val expected = items.valid

    val actual = items.traverse(_.validate)

    actual mustBe expected
  }

}
