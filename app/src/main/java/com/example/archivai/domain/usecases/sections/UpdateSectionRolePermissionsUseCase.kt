package com.example.archivai.domain.usecases.sections

import com.example.archivai.domain.repository.sections.SectionsRepository
import javax.inject.Inject

class UpdateSectionRolePermissionsUseCase @Inject constructor(
    private val sectionRepository: SectionsRepository
) {
    suspend operator fun invoke(
        sectionId: Int,
        roleId: Int,
        actions: List<Int>
    ) =
        sectionRepository.updateRoleSectionPermissions(roleId, sectionId, actions)
}
