package com.example.archivai.domain.usecases.sections

import com.example.archivai.domain.repository.sections.SectionsRepository
import javax.inject.Inject

class UpdateSectionEmployeePermissionsUseCase @Inject constructor(
    private val sectionRepository: SectionsRepository
) {
    suspend operator fun invoke(
        sectionId: Int,
        employeeId: Int,
        actions: List<Int>
    ) =
        sectionRepository.updateUserSectionPermissions(sectionId = sectionId, userId = employeeId, sectionActions = actions)
}