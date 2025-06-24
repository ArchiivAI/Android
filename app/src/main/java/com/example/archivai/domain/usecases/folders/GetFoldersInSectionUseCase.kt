package com.example.archivai.domain.usecases.folders

import com.example.archivai.domain.repository.folders.FoldersRepository
import javax.inject.Inject

class GetFoldersInSectionUseCase @Inject constructor(
    private val foldersRepository: FoldersRepository,

) {

    suspend operator fun invoke(sectionId: Int) = foldersRepository.getFoldersInSection(sectionId)
}
