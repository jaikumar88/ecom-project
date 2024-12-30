package com.infogain.router

import com.infogain.service.PaymentServiceImpl
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route

fun Route.paymentRoutes(productService: PaymentServiceImpl) {

    route("/payment"){

        get {  }

        post {  }

        put {  }

        delete {  }
    }
}