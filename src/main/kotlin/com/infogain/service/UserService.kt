package com.infogain.service

import com.infogain.models.User
import com.infogain.responsepayload.UserResponsePayload

interface UserService {

    fun getAllUsers(): List<User>
    fun createUser(user: User): Int
    fun getUserById(id: Int): UserResponsePayload
    fun delete(userId:Int): Boolean
    fun update(user: User): Boolean
}