package com.infogain.extension



import com.infogain.models.User
import com.infogain.requestpayload.UserRequestPayload

import com.infogain.responsepayload.UserResponsePayload

fun UserRequestPayload.toUser(
    id: Int = 0,
    createdAt: String = "",
    updatedAt: String = ""
): User {
    return User(
        id = id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        password = "",
        role = this.roleId,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun User.toResponsePayload(): UserResponsePayload {
    return UserResponsePayload(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        role = this.role,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}
