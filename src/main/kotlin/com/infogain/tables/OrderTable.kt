package com.infogain.tables


import org.jetbrains.exposed.sql.Table

object OrderTable : Table("Order") {
    val id = integer("id").autoIncrement()
    val user = reference("user_id", UsersTable.id)
    val payment = reference("pay_id", PaymentTable.id)
    val subTotal = double("sub_total")
    val discount = double("discount_amount")
    val orderTax = double("tax")
    val orderAmount = double("amount")
}

