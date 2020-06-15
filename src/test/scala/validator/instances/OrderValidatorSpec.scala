package validator.instances

import cats.implicits._
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

}
