package com.example.archivai.domain.usecases.folders

import com.example.archivai.domain.repository.folders.FoldersRepository
import javax.inject.Inject

class CreateFolderUseCase@Inject constructor(
    private val repository:FoldersRepository
) {
    suspend operator fun invoke(
        folderName: String,
       sectionId : Int
    ) = repository.createFolderInSection(
        sectionId = sectionId,
        folderName = folderName
    )
}