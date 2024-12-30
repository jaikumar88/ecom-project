package com.infogain.router

import com.infogain.extension.toProduct
import com.infogain.requestpayload.ProductRequestPayload
import com.infogain.service.ProductServiceImpl
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route

/**
 *  This class is responsible to expose all the end point related with product
 *  e.g get all products, search product by id, search product by name, description
 *  or
 */
fun Route.productRoutes(productService: ProductServiceImpl) {

    route("/product") {
        /**
         * get all products
         */
        get {
            val products = productService.getAllProducts()
            if (products.isEmpty()) {
                call.respond(HttpStatusCode.NoContent, "No product available")
            } else {
                call.respond(HttpStatusCode.OK, products)
            }
            return@get
        }

        /**
         * get product by id
         */
        get("{id}") {
            val productId = call.parameters["id"]
            if (productId == null) {
                call.respond(HttpStatusCode.NoContent, "No data found")
            } else {
                val product = productService.getProductById(productId.toString())
                call.respond(HttpStatusCode.OK, product)
            }
            return@get
        }

        post("save") {

            val product = call.receive<ProductRequestPayload>()
            if (product == null) {
                call.respond(HttpStatusCode.BadRequest, "Bad Request")
            } else {
                val productId = productService.createProduct(product.toProduct())
                call.respond(HttpStatusCode.Created, "Product created : ${productId}")
            }
            return@post
        }

        put {
            val product = call.receive<ProductRequestPayload>()
            if (product == null) {
                call.respond(HttpStatusCode.BadRequest, "Bad Request")
            } else {
                val productId = productService.updateProduct(product.toProduct())
                call.respond(HttpStatusCode.Created, "Product created : ${productId}")
            }

        }

        delete {


        }
    }
}