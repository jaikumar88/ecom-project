package com.infogain.service

import com.infogain.models.Product
import com.infogain.repo.ProductRepository

class ProductServiceImpl(val productRepository: ProductRepository) : ProductService {

    override fun createProduct(product: Product): String {
        return productRepository.createProduct(product)

    }

    override fun getProductById(id: String): Product {
        return productRepository.getProductById(id)
    }

    override fun updateProduct(product: Product): Boolean {
       return productRepository.updateProduct(product)
    }

    override fun getAllProducts(): List<Product> {
        val products = productRepository.getAllProduct()
        val limitedRecords = products.take(20)
        return limitedRecords
    }


}