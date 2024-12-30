package com.infogain.requestpayload

import kotlinx.serialization.Serializable

@Serializable
data class CategoryRequestPayload(
    val name: String,
    val description: String
)