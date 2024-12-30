package com.infogain.repo

import com.infogain.models.Role
import com.infogain.models.User
import com.infogain.tables.RoleTable
import com.infogain.tables.UsersTable
import com.infogain.tables.UsersTable.email
import com.infogain.tables.UsersTable.firstName
import com.infogain.tables.UsersTable.lastName
import com.infogain.tables.UsersTable.password
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class RoleRepositoryImpl: RoleRepository {
    override fun create(role: Role): Boolean = transaction() {

        RoleTable.insert {
            it[name] = role.name
            it[description] = role.description
        } [RoleTable.id] > 0
    }

    override fun update(role: Role): Boolean {
//        RoleTable.apply {
//            it[name] = role.name
//            it[description] = role.description
//        }
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Role> = transaction(){
        RoleTable.selectAll().map {
            it.toRole()
        }
    }

    private fun ResultRow.toRole() = Role(
        id = this[RoleTable.id],
        name = this[RoleTable.name],
        description = this[RoleTable.description],
    )

    override fun findRoleById(id: Int): Role = transaction(){
        // select * from UsersTable where id = ?(parameter id value)
        RoleTable.selectAll().where { RoleTable.id eq id }.map {
            it.toRole()
        }.singleOrNull() as Role
    }

    override fun delete(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}