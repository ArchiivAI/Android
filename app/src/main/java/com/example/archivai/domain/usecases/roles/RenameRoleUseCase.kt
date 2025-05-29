package com.example.archivai.domain.usecases.roles

import com.example.archivai.domain.repository.roles.RoleRepository
import javax.inject.Inject

class RenameRoleUseCase @Inject constructor( private val roleRepository: RoleRepository) {
    suspend operator fun invoke(roleId:Int,newName: String) = roleRepository.renameRole(roleId,newName)

}