package com.infogain.extension



import com.infogain.models.Product

import com.infogain.requestpayload.ProductRequestPayload
import kotlinx.io.files.SystemFileSystem
import kotlin.String


fun ProductRequestPayload.toProduct(

    createdAt: String = System.currentTimeMillis().toString(),
    updatedAt: String = System.currentTimeMillis().toString()
): Product {
    return Product(
        id = this.id,
        name = this.name,
        description = this.description,
        categoryId = this.categoryId,
        price = this.price,
        stock = this.stock,
        imageUrl = this.imageUrl,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun Product.toProductResponsePayload(): ProductRequestPayload {
    return ProductRequestPayload(
        id = this.id,
        name = this.name,
        description = this.description,
        categoryId = this.categoryId,
        price = this.price,
        stock = this.stock,
        imageUrl = this.imageUrl.toString(),
    )
}
