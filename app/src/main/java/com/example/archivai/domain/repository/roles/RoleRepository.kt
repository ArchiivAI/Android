package com.example.archivai.domain.repository.roles

import com.example.archivai.domain.entities.Employee
import com.example.archivai.domain.entities.Role

interface RoleRepository {
    suspend fun getRoles(): Result<List<Role>>

    suspend fun deleteRole(roleId: Int): Result<Unit>

    suspend fun createRole(roleName: String): Result<Unit>

    suspend fun getEmployeesInRole(roleId: Int): Result<List<Employee>>

    suspend fun deleteEmployeeFromRole(roleId: Int, employeeId: Int): Result<Unit>

    suspend fun addEmployeeToRole(roleId: Int, employeeId: Int): Result<Unit>

    suspend fun getRoleDetails(roleId: Int): Result<Role>


}