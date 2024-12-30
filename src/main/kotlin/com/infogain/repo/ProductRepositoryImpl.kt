package com.infogain.repo

import com.infogain.models.Product
import com.infogain.tables.ProductsTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ProductRepositoryImpl: ProductRepository {

    override fun getAllProduct(): List<Product> = transaction() {
        ProductsTable.selectAll( ).map {
            it.toProduct()
        }
    }

    override fun createProduct(product: Product): String = transaction {
        ProductsTable.insert {
            it[id] = product.id
            it[name] = product.name
            it[description] = product.description
            it[categoryId] = product.categoryId
            it[price] = product.price
            it[stock] = product.stock
            it[imageUrl] = product.imageUrl
            it[createdAt] = product.createdAt
            it[updatedAt] = product.updatedAt
        }[ProductsTable.id]

    }

    override fun getProductById(productId: String): Product = transaction {
        ProductsTable.selectAll().where (ProductsTable.id eq productId ).map {
            it.toProduct()
        }.singleOrNull() as Product
    }

    override fun updateProduct(product: Product): Boolean {
        TODO("Not yet implemented")
    }


    private fun ResultRow.toProduct() = Product (
            id = this[ProductsTable.id],
            name = this[ProductsTable.name],
            description = this[ProductsTable.description],
            categoryId = this[ProductsTable.categoryId],
            price = this[ProductsTable.price],
            stock = this[ProductsTable.stock],
            imageUrl = this[ProductsTable.imageUrl],
            createdAt = this[ProductsTable.createdAt],
            updatedAt = this[ProductsTable.updatedAt]
        )




}