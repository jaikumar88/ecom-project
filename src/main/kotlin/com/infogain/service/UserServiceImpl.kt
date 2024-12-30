package com.infogain.service

import com.infogain.extension.toResponsePayload
import com.infogain.models.User
import com.infogain.repo.UserRepository
import com.infogain.responsepayload.UserResponsePayload
import org.slf4j.LoggerFactory
import org.slf4j.Logger

class UserServiceImpl(val userRepository: UserRepository) : UserService {

    private val logger: Logger = LoggerFactory.getLogger(UserServiceImpl:: class.java)

    override fun createUser(user: User): Int {
        return userRepository.createUser(user)
    }

    override fun getUserById(id: Int): UserResponsePayload = userRepository.getUserById(id)!!.toResponsePayload()
    override fun delete(userId: Int): Boolean {
        return userRepository.delete(userId)
    }

    override fun update(user: User): Boolean {
        logger.info("Inside update user")
        return  userRepository.update(user)
    }

    override fun getAllUsers(): List<User> = userRepository.getAllUsers()



}