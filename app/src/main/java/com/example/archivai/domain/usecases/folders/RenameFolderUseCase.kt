package com.example.archivai.domain.usecases.folders

import com.example.archivai.domain.repository.folders.FoldersRepository
import javax.inject.Inject

class RenameFolderUseCase @Inject constructor(
  val repository: FoldersRepository
) {
    suspend operator fun invoke(folderName:String,folderId : Int) =
        repository.renameFolder(folderName,folderId)
}