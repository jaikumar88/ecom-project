package com.infogain.repo

import com.infogain.models.Order
import com.infogain.tables.OrderTable
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert

class OrderRepositoryImpl: OrderRepository {

       override fun getAllOrders(): List<Order> = transaction() {
            OrderTable.selectAll().map {
                Order(
                    id = it[OrderTable.id],
                    userId = it[OrderTable.user],
                    paymentId = it[OrderTable.payment],
                    subTotal = it[OrderTable.subTotal],
                    discount = it[OrderTable.discount],
                    orderTax = it[OrderTable.orderTax],
                    orderAmount = it[OrderTable.orderAmount]
                )
            }
        }

    override fun getOrderById(orderId: Int): Order? = transaction {
            OrderTable.select ( OrderTable.id eq orderId ).mapNotNull {
                Order(
                    id = it[OrderTable.id],
                    userId = it[OrderTable.user],
                    paymentId = it[OrderTable.payment],
                    subTotal = it[OrderTable.subTotal],
                    discount = it[OrderTable.discount],
                    orderTax = it[OrderTable.orderTax],
                    orderAmount = it[OrderTable.orderAmount]
                )
            }.singleOrNull()
        }

    override   fun addOrder(order: Order): Int = transaction() {
            OrderTable.insert {
                it[user] = order.userId
                it[payment] = order.paymentId
                it[subTotal] = order.subTotal
                it[discount] = order.discount
                it[orderTax] = order.orderTax
                it[orderAmount] = order.orderAmount
            } [OrderTable.id]
        }

    override   fun deleteOrder(orderId: Int): Boolean = transaction {
            OrderTable.deleteWhere() { OrderTable.id eq orderId } > 0
        }


}