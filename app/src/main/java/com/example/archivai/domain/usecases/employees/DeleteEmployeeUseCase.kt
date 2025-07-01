package com.example.archivai.domain.usecases.employees

import com.example.archivai.domain.repository.employees.EmployeesRepository
import javax.inject.Inject

class DeleteEmployeeUseCase @Inject constructor(
    private val employeesRepository: EmployeesRepository
) {
    suspend operator fun invoke(employeeId: Int): Result<Unit> =
         employeesRepository.deleteEmployee(employeeId)

}