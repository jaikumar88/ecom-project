package com.infogain.models

class Order (
    val id: Int?,
    val userId: Int,
    val paymentId: Int,
    val subTotal: Double,
    val discount: Double,
    val orderTax: Double,
    val orderAmount: Double
)