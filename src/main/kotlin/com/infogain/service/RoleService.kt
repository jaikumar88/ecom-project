package com.infogain.service

import com.infogain.models.Role

interface RoleService {
    fun create(role: Role): Boolean
    fun update(role: Role): Boolean
    fun findAll(): List<Role>
    fun findRoleById(id: Int): Role
    fun delete(id:Int): Boolean
}