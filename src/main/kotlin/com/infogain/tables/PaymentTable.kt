package com.infogain.tables

import org.jetbrains.exposed.sql.IDateColumnType
import org.jetbrains.exposed.sql.Table


object PaymentTable: Table("payment") {
    val id = integer("id").autoIncrement()
    val userId = reference("user_id", UsersTable.id)
    val paymentType = varchar("payment_type",50)
    val amount =  double("amount")
    val createdTime= varchar("createdTime", 50)
    val updatedTime= varchar("updatedTime", 50)
}
