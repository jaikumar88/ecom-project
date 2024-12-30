package com.infogain.service

import com.infogain.models.Product

interface ProductService {

    fun getAllProducts(): List<Product>
    fun createProduct( product : Product): String
    fun getProductById(id: String): Product
    fun updateProduct(product: Product) : Boolean

}