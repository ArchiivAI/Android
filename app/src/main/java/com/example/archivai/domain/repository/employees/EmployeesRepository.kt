package com.example.archivai.domain.repository.employees

import androidx.compose.ui.semantics.Role
import com.example.archivai.domain.entities.Employee

interface EmployeesRepository {

    suspend fun deleteEmployee(employeeId: Int): Result<Unit>

    suspend fun getEmployees(): List<Employee>

    suspend fun addEmployee(firstName: String , lastName : String, email: String , roles : List<Role>): Result<Unit>

    suspend fun updateEmployee(
        employeeId: Int,
        firstName: String,
        lastName: String,
    ): Result<Unit>

    suspend fun getEmployeeRoles(
        employeeId: Int
    ): Result<List<Role>>
}