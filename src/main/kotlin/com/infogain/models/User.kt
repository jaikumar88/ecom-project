package com.infogain.models

import kotlinx.serialization.Serializable

@Serializable
data class User (
    val id: Int,
    val firstName: String ,
    val lastName: String ,
    val email: String ,
    val password: String ,
    val roles: Role ,
    val createdAt: String ,
    val updatedAt: String
 )