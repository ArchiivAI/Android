package com.example.archivai.domain.usecases.sections

import com.example.archivai.domain.repository.sections.SectionsRepository
import javax.inject.Inject

class DeleteSectionUseCase @Inject constructor(private val sectionRepository: SectionsRepository) {

    suspend operator fun invoke(sectionId: Int) {
        sectionRepository.deleteSection(sectionId)
    }
}