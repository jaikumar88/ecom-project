package com.infogain

import com.infogain.config.DatabaseConfig
import com.infogain.router.rootRoutes
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.request.uri
import io.ktor.server.routing.routing
import io.ktor.util.logging.Logger
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.transactions.transaction



    fun main(args: Array<String>) {
        embeddedServer(Netty, port = System.getenv("PORT")?.toInt() ?: 8080) {
            module()
        }.start(wait = true)
    }


    fun Application.module() {
        //configureRouting()
        // json conversion
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
            })

        }

        // Route configure
        routing {
            rootRoutes()

        }

        install(CORS) {
            anyHost() // Allow all origins for development
            allowMethod(HttpMethod.Get)
            allowMethod(HttpMethod.Post)
            allowMethod(HttpMethod.Delete)
            allowMethod(HttpMethod.Put)
            allowHeader(HttpHeaders.ContentType)
        }


        intercept(ApplicationCallPipeline.Monitoring) {
            println("Processing request: ${call.request.uri}")
        }
        intercept(ApplicationCallPipeline.Fallback) {
            println("Finished processing request: ${call.response.status()}")
        }

        // Database initialization

        DatabaseConfig.init()


    }
