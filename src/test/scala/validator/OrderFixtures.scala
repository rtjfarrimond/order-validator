package validator

import java.time.LocalDate
import java.util.UUID

import model._

trait OrderFixtures {

  val validAddress: Address = Address("123", "Fake Street", None, "London", None, "N6 6TE")
  val validCustomer: Customer = Customer("firstName", "lastName", LocalDate.now().minusYears(18), validAddress)
  val validItem: Item = Item(UUID.randomUUID(), "name", 1, 10, None)
  val validItems: List[Item] = List(validItem)
  val validPaymentDetails: PaymentDetails = PaymentDetails("accountHolder", "12345678", "123456")
  val validOrder: Order = Order(validCustomer, validItems, validPaymentDetails)

}
