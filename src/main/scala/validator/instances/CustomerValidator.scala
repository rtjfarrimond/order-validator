package validator.instances

import java.time.{LocalDate, Period}

import cats.data.ValidatedNec
import cats.implicits._
import error.ValidationError
import model.Customer
import validator.Validator
import validator.Validator._

object CustomerValidator extends Validator[Customer] {

  def validate(customer: Customer): ValidatedNec[ValidationError, Customer] = {
    (
      Validator.nonEmptyString(customer.firstName)("customer.firstName"),
      Validator.nonEmptyString(customer.lastName)("customer.lastName"),
      atLeastThirteenYearsOld(customer.dateOfBirth),
      customer.address.validate
      ).mapN(Customer)
  }

  def atLeastThirteenYearsOld(dateOfBirth: LocalDate): ValidatedNec[ValidationError, LocalDate] = {
    lazy val now = LocalDate.now()
    lazy val yearsOld = Period.between(dateOfBirth, now).getYears
    if (yearsOld < 13)
      ValidationError("customer.dateOfBirth", "Must be at least 13 years old").invalidNec
    else
      dateOfBirth.valid
  }

}
