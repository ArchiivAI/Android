package com.example.archivai.domain.usecases.sections

import com.example.archivai.domain.repository.sections.SectionsRepository
import javax.inject.Inject

class GetSectionsUseCase @Inject constructor(val repository : SectionsRepository) {
    suspend operator fun invoke() = repository.getSections()
}