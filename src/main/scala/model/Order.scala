package model

import java.util.UUID

case class Order(customer: Customer, items: List[Item], paymentDetails: PaymentDetails)

case class Item(uuid: UUID, name: String,  weightKg: Int, cost: Int, ageRestriction: Option[Int])

case class PaymentDetails(accountHodler: String, accountNumber: String, sortCode: String)

