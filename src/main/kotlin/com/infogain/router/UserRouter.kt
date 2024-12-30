package com.infogain.router

import com.infogain.extension.toUser
import com.infogain.requestpayload.UserRequestPayload
import com.infogain.service.UserServiceImpl
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import sun.util.logging.resources.logging

private val logger: Logger = LoggerFactory.getLogger("UserRouter")
/**
 * This class responsible for exposing all the end points related with User
 * eg. Get all User, Search user by id, search user by email or name
 */
fun Route.userRoutes(userService: UserServiceImpl) {

    /**
     * Base context path for user end points
     */
    route("/users") {
        // Get all users from database this is generic search
        get() {
            try {
                val users = userService.getAllUsers()
                if (users.isEmpty()) {
                    call.respond(HttpStatusCode.NoContent)
                    return@get
                } else {
                    call.respond(users)
                    return@get
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // @GetMapping
        // get user by id
        get("{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid ID")
                return@get
            }
            val user = userService.getUserById(id)

            if (user.equals(null)) {
                call.respond(HttpStatusCode.NotFound, "User not found")
            } else {
                call.respond(HttpStatusCode.OK, user)
            }
            return@get
        }


        /**
         * Method to save user
         */
        post() {

            val userRequest = call.receive<UserRequestPayload>()
            val number = userService.createUser(userRequest.toUser())
            call.respond(HttpStatusCode.OK, "User created : ${number}")
            return@post
        }

        put() {
            try {
                logger.info("Update user router")
                val userRequest = call.receive<UserRequestPayload>()
                val number = userService.update(userRequest.toUser())
                call.respond(HttpStatusCode.OK, "User created : ${number}")
            } catch (e: Exception){
                e.printStackTrace()
            }
            return@put
        }

        delete("/{id}") {
            try {
                val userId = call.pathParameters["id"]!!.toInt()
                print("UserId : ${userId}")
                val isDeleted = userService.delete(userId)
                if(isDeleted){
                    call.respond(HttpStatusCode.OK, "User deleted successfully")
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Unable to delete records${userId}")
                }

            }catch (e: Exception){
                e.printStackTrace()
            }
            return@delete
        }

    }

}