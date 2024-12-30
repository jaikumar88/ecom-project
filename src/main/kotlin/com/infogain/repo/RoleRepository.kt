package com.infogain.repo

import com.infogain.models.Role

interface RoleRepository {
    fun create(role: Role): Boolean
    fun update(role: Role): Boolean
    fun findAll(): List<Role>
    fun findRoleById(id:Int): Role
    fun delete(id: Int): Boolean
}