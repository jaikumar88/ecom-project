package com.infogain.tables

import org.jetbrains.exposed.sql.Table


object UsersTable: Table("USERS") {
    val id = integer("id").autoIncrement()
    val firstName = varchar("firstName", 255)
    val lastName = varchar("lastName", 255)
    val email = varchar("email", 255)
    val password = varchar("password", 255)
    val role = reference("role_id", RoleTable.id) // Forign key
    val createdAt = varchar ("created_at", 255)
    val updatedAt = varchar("updated_at", 255)
    override val primaryKey = PrimaryKey(id)
}