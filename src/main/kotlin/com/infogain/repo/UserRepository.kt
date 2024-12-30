package com.infogain.repo

import com.infogain.models.User
import org.jetbrains.exposed.sql.ResultRow

interface UserRepository {

    fun getAllUsers(): List<User>

    fun createUser(user: User): Int
    fun getUserById(id: Int): User?
    fun delete(userId: Int): Boolean
    fun update(user: User): Boolean
}