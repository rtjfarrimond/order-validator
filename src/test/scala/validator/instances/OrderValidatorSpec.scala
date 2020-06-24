package validator.instances

import java.util.UUID

import cats.implicits._
import error.ValidationError
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import validator.OrderFixtures
import validator.Validator._

class OrderValidatorSpec extends AnyFlatSpec with Matchers with OrderFixtures {

  "validate" must "return valid given a valid order" in {
    val expected = validOrder.valid
    val actual = validOrder.validate

    actual mustBe expected
  }

  it must "return invalid if customer is not old enough to buy age restricted items" in {
    val beer = validItem.copy(uuid = UUID.randomUUID(), name = "beer", ageRestriction = Some(18))
    val order = validOrder.copy(items = beer :: validItems)
    val expected = ValidationError(
      "item.ageRestriction",
      s"Must be at least ${beer.ageRestriction.get} years old to buy item ${beer.uuid}"
    ).invalidNec

    val actual = order.validate

    actual mustBe expected
  }

}
