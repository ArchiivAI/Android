package com.example.archivai.data.repository.roles

import com.example.archivai.data.mappers.toDomain
import com.example.archivai.data.source.remote.endpoint.roles.RolesApiService
import com.example.archivai.data.utils.SharedPrefsHelper
import com.example.archivai.domain.entities.Employee
import com.example.archivai.domain.entities.Role
import com.example.archivai.domain.repository.roles.RoleRepository
import javax.inject.Inject

class RolesRepositoryImpl @Inject constructor(val rolesApiService: RolesApiService) : RoleRepository{
    val token =  "Bearer ${SharedPrefsHelper.getToken()}"
    override suspend fun getRoles(): List<Role> {
        return rolesApiService.getRoles(token)
            .map { it.toDomain() }
    }

    override suspend fun deleteRole(roleId: Int): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun createRole(roleName: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getEmployeesInRole(roleId: Int): Result<List<Employee>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEmployeeFromRole(
        roleId: Int,
        employeeId: Int
    ): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun addEmployeeToRole(
        roleId: Int,
        employeeId: Int
    ): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getRoleDetails(roleId: Int): Result<Role> {
        TODO("Not yet implemented")
    }
}