package com.example.archivai.data.repository.employees

import android.util.Log
import androidx.compose.ui.semantics.Role
import com.example.archivai.data.mappers.toDomainModel
import com.example.archivai.data.source.remote.endpoint.employees.EmployeesApiService
import com.example.archivai.data.utils.SharedPrefsHelper
import com.example.archivai.domain.entities.Employee
import com.example.archivai.domain.repository.employees.EmployeesRepository
import javax.inject.Inject

class EmployeesRepositoryImpl @Inject constructor(
    val  employeesApiService: EmployeesApiService) : EmployeesRepository{
    val token =  "Bearer ${SharedPrefsHelper.getToken()}"
    override suspend fun deleteEmployee(employeeId: Int): Result<Unit> {
        try {
            val response = employeesApiService.deleteEmployee(token, employeeId)
            return if (response.message.contains("deleted successfully")){
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to delete employee"))
            }
        }catch (e: Exception){
            Log.e("EmployeeRepository", "Error deleting employee: ${e.message}")
            return Result.failure(e)
        }
    }

    override suspend fun getEmployees(): List<Employee> {
        return employeesApiService.getEmployees(token)
            .map { it.toDomainModel() }
    }

    override suspend fun addEmployee(
        firstName: String,
        lastName: String,
        email: String,
        roles: List<Role>
    ): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun updateEmployee(
        employeeId: Int,
        firstName: String,
        lastName: String
    ): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getEmployeeRoles(employeeId: Int): Result<List<Role>> {
        TODO("Not yet implemented")
    }
}