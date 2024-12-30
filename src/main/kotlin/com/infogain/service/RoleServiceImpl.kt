package com.infogain.service

import com.infogain.models.Role
import com.infogain.repo.RoleRepository

class RoleServiceImpl(private val roleRepository: RoleRepository): RoleService {
    override fun create(role: Role): Boolean {
        return roleRepository.create(role)
    }

    override fun update(role: Role): Boolean {
        return roleRepository.update(role)
    }

    override fun findAll(): List<Role> {
        return roleRepository.findAll()
    }

    override fun findRoleById(id: Int): Role {
       return roleRepository.findRoleById(id)
    }

    override fun delete(id: Int): Boolean {
        return roleRepository.delete(id)
    }
}