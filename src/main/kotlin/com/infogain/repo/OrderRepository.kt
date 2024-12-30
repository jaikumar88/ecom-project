package com.infogain.repo

import com.infogain.models.Order

interface OrderRepository {
    fun getAllOrders(): List<Order>
    fun getOrderById(orderId: Int): Order?
    fun addOrder(order: Order): Int
    fun deleteOrder(orderId: Int): Boolean
}