package com.example.archivai.domain.usecases.employees

import com.example.archivai.domain.repository.employees.EmployeesRepository
import javax.inject.Inject

class CreateEmployeeUseCase @Inject constructor(
    private val employeesRepository: EmployeesRepository
) {
    suspend operator fun invoke(
        firstName: String,
        lastName: String,
        email: String,
        roleIds: List<Int>
    ): Result<Unit> {
        return employeesRepository.addEmployee(
            firstName = firstName,
            lastName = lastName,
            email = email,
            roleIds = roleIds
        )
    }
}