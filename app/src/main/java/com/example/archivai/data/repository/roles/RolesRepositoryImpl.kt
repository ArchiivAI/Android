package com.example.archivai.data.repository.roles

import android.util.Log
import com.example.archivai.data.mappers.toDomain
import com.example.archivai.data.source.remote.endpoint.roles.RolesApiService
import com.example.archivai.data.utils.SharedPrefsHelper
import com.example.archivai.domain.entities.Employee
import com.example.archivai.domain.entities.Role
import com.example.archivai.domain.repository.roles.RoleRepository
import javax.inject.Inject

class RolesRepositoryImpl @Inject constructor(val rolesApiService: RolesApiService) :
    RoleRepository {
    val token = "Bearer ${SharedPrefsHelper.getToken()}"
    override suspend fun getRoles(): List<Role> {
        return rolesApiService.getRoles(token)
            .map { it.toDomain() }
    }

    override suspend fun deleteRole(roleId: Int): Result<Unit> {
        try {
            val response = rolesApiService.deleteRole(roleId, token)
            return if (response.message.contains("deleted successfully")) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to delete role"))
            }
        } catch (e: Exception) {
            Log.e("RolesRepository", "Error deleting role: ${e.message}")
            return Result.failure(e)
        }

    }

    override suspend fun renameRole(
        roleId: Int,
        newName: String
    ): Result<Unit> {
        try {
            val response = rolesApiService.renameRole(roleId, newName, token)
            return if (response.message.contains("role name updated")) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to rename Role"))
            }
        } catch (e: Exception) {
            Log.e("RoleRepository", "Error renaming role: ${e.message}")
            return Result.failure(e)
        }
    }

    override suspend fun createRole(roleName: String, employeeIds: List<Int>): Result<Unit> {
        try {
            rolesApiService.createRole(token, roleName, employeeIds)
                .let { response ->
                    return if (response.message.contains("Role added")) {
                        Result.success(Unit)
                    } else {
                        Result.failure(Exception("Failed to create role"))
                    }
                }

        } catch (e: Exception) {
            Log.e("RolesRepository", "Error adding role: ${e.message}")
            return Result.failure(e)
        }

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