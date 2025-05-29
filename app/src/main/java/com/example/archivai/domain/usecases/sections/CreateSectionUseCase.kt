package com.example.archivai.domain.usecases.sections


import com.example.archivai.domain.repository.sections.SectionsRepository
import javax.inject.Inject

class CreateSectionUseCase @Inject constructor(private val repository: SectionsRepository) {
    suspend operator fun invoke(
        name: String
    ) = repository.createSection(
        name = name
    )
}