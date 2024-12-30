package com.infogain.repo

import com.infogain.models.User
import com.infogain.tables.UsersTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class UserRepositoryImpl : UserRepository {

    val roleRepository = RoleRepositoryImpl()

    override fun getAllUsers(): List<User> = transaction {
        UsersTable.selectAll().map {
            it.toUser()
        }
    }


    private fun ResultRow.toUser() = User(
        id = this[UsersTable.id],
        firstName = this[UsersTable.firstName],
        lastName = this[UsersTable.lastName],
        email = this[UsersTable.email],
        password = this[UsersTable.password],
        role = this[UsersTable.role].toString(),
        createdAt = this[UsersTable.createdAt],
        updatedAt = this[UsersTable.updatedAt]
    )

    override fun createUser(user: User): Int = transaction {
        val userRole = roleRepository.findRoleById(user.role.toInt())
            ?: throw IllegalArgumentException("Role with ID ${user.role} not found.")

        UsersTable.insert {
            it[firstName] = user.firstName
            it[lastName] = user.lastName
            it[email] = user.email
            it[password] = user.password
            it[role] = userRole.id ?: 0
            it[createdAt] = user.createdAt
            it[updatedAt] = user.updatedAt
        }[UsersTable.id]
    }

    override fun getUserById(userId: Int): User? = transaction {
        // select * from UsersTable where id = ?(parameter id value)

        UsersTable.selectAll().where { UsersTable.id eq userId }.map {
            it.toUser()
        }.singleOrNull() as User
    }

    override fun delete(userId: Int): Boolean {
        return transaction {
            UsersTable.deleteWhere { UsersTable.id eq userId } > 0
        }
    }

    override fun update(user: User): Boolean = transaction() {
        val userRole = roleRepository.findRoleById(user.role.toInt())
            ?: throw IllegalArgumentException("Role with ID ${user.role} not found.")
        UsersTable.update({ UsersTable.id eq user.id }) {
            it[firstName] = user.firstName
            it[lastName] = user.lastName
            it[email] = user.email
            it[password] = user.password

        } > 0

    }
}