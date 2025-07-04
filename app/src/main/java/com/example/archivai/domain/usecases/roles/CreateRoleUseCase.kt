package com.example.archivai.domain.usecases.roles

import com.example.archivai.domain.repository.roles.RoleRepository
import javax.inject.Inject

class CreateRoleUseCase @Inject constructor(
    private val roleRepository: RoleRepository
) {
    suspend operator fun invoke(roleName: String, employeeIds: List<Int>): Result<Unit> =
        roleRepository.createRole(roleName, employeeIds)

}