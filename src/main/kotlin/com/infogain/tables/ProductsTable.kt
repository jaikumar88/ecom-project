package com.infogain.tables

import org.jetbrains.exposed.sql.Table


object ProductsTable : Table("products") {
    val id = varchar("id", 36)
    val name = varchar("name", 255)
    val description = varchar("description", 500)
    val categoryId = varchar("category_id", 36)
    val price = double("price")
    val stock = integer("stock")
    val imageUrl = varchar("image_url", 500).nullable()
    val createdAt = varchar("created_at", 255)
    val updatedAt = varchar("updated_at", 255)

    override val primaryKey = PrimaryKey(id)
}