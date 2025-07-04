package com.example.archivai.domain.repository.roles

import com.example.archivai.domain.entities.Employee
import com.example.archivai.domain.entities.Role

interface RoleRepository {
    suspend fun getRoles(): List<Role>

    suspend fun deleteRole(roleId: Int): Result<Unit>

    suspend fun renameRole(roleId: Int , newName: String) : Result<Unit>

    suspend fun createRole(roleName: String,employeeIds : List<Int>): Result<Unit>

    suspend fun getEmployeesInRole(roleId: Int): Result<List<Employee>>

    suspend fun deleteEmployeeFromRole(roleId: Int, employeeId: Int): Result<Unit>

    suspend fun addEmployeeToRole(roleId: Int, employeeId: Int): Result<Unit>

    suspend fun getRoleDetails(roleId: Int): Result<Role>


}