package com.example.archivai.domain.usecases.folders

import com.example.archivai.domain.repository.folders.FoldersRepository
import javax.inject.Inject

class GetFolderInFolderUseCase @Inject constructor(
    private val foldersRepository: FoldersRepository,
) {

    suspend operator fun invoke(folderId: Int) =
        foldersRepository.getFoldersInFolder(folderId)

}
