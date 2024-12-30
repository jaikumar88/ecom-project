package com.infogain.router

import com.infogain.models.Role
import com.infogain.service.RoleService
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import org.slf4j.LoggerFactory

fun Route.roleRoutes(roleService: RoleService){
    val logger = LoggerFactory.getLogger("RoleRoute")
    route("roles"){
        get {
            call.respond(roleService.findAll())
            return@get
        }
        get ("{id}"){

        }
        post {
            logger.info("Entering into save role route")
            try {
                val role = call.receive<Role>()
                val isUpdated = roleService.create(role)
                logger.info("Role created successfully ${isUpdated}")
                if (isUpdated) {
                    call.respond(HttpStatusCode.OK, "Record created successfully")
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Unable to create Role")
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
            return@post
        }
        put {

        }
        delete {

        }
    }
}