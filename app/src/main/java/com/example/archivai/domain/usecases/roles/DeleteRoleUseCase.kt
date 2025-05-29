package com.example.archivai.domain.usecases.roles

import com.example.archivai.domain.repository.roles.RoleRepository
import javax.inject.Inject

class DeleteRoleUseCase @Inject constructor(private val roleRepository: RoleRepository) {

    suspend operator fun invoke(roleId: Int) =
        roleRepository.deleteRole(roleId)

}