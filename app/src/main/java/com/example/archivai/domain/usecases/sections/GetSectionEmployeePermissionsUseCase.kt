package com.example.archivai.domain.usecases.sections

import com.example.archivai.domain.repository.sections.SectionsRepository
import javax.inject.Inject

class GetSectionEmployeePermissionsUseCase @Inject constructor(
    private val sectionRepository: SectionsRepository
) {
    suspend operator fun invoke(sectionId: Int, employeeId: Int) =
        sectionRepository.getUserSectionPermissions(sectionId, employeeId)
}