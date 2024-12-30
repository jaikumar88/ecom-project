package com.infogain.requestpayload


import kotlinx.serialization.Serializable

@Serializable
data class UserRequestPayload(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val roleId: String
)



