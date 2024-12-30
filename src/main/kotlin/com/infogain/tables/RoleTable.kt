package com.infogain.tables

import org.jetbrains.exposed.sql.Table


object RoleTable : Table("Role") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 50)
    val description = varchar("description", 255)
    val createdAt = varchar("created_at", 50).default(System.currentTimeMillis().toString())
    val updatedAt = varchar("updated_at", 50).default(System.currentTimeMillis().toString())
    override val primaryKey = PrimaryKey(id)
}