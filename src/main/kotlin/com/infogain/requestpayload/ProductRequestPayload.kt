package com.infogain.requestpayload


import kotlinx.serialization.Serializable

@Serializable
data class ProductRequestPayload(
    val id: String,
    val name: String,
    val description: String,
    val categoryId: String,
    val price: Double,
    val stock: Int,
    val imageUrl: String
)



