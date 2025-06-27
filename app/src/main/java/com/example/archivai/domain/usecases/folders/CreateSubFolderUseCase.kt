package com.example.archivai.domain.usecases.folders

import com.example.archivai.domain.repository.folders.FoldersRepository
import javax.inject.Inject

class CreateSubFolderUseCase @Inject constructor(
    private val foldersRepository: FoldersRepository,
) {

    suspend operator fun invoke(
        folderName: String,
        parentFolderId: Int
    ) = foldersRepository.createSubFolderInFolder(
        folderName = folderName,
        folderId = parentFolderId
    )
}
