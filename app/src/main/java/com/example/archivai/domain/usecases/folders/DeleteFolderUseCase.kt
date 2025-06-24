package com.example.archivai.domain.usecases.folders

import com.example.archivai.domain.repository.folders.FoldersRepository
import javax.inject.Inject

class DeleteFolderUseCase @Inject constructor(
    val repository : FoldersRepository
) {
    suspend operator fun invoke(folderId: Int) = repository.deleteFolder(folderId)
}