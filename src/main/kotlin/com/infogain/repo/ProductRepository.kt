package com.infogain.repo

import com.infogain.models.Product
import org.jetbrains.exposed.sql.ResultRow

interface ProductRepository {

    fun getAllProduct(): List<Product>
    // List<Object>
    // while(rs.next) ==> rs ==> particular reffered row

    fun createProduct(product: Product): String
    fun getProductById(id: String): Product
    fun updateProduct(product: Product): Boolean

}