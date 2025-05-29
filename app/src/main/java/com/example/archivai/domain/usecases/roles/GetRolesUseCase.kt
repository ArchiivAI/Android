package com.example.archivai.domain.usecases.roles

import com.example.archivai.domain.repository.roles.RoleRepository
import javax.inject.Inject

class GetRolesUseCase @Inject constructor( private val rolesRepository: RoleRepository) {
    suspend operator fun invoke() = rolesRepository.getRoles()



}