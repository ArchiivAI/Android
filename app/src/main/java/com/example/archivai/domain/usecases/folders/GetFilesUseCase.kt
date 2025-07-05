package com.example.archivai.domain.usecases.folders

import android.util.Log
import com.example.archivai.domain.entities.File
import com.example.archivai.domain.repository.folders.FoldersRepository
import javax.inject.Inject

class GetFilesUseCase @Inject constructor(
    private val foldersRepository: FoldersRepository
) {

    suspend operator fun invoke(folderId: Int): List<File> {
        val  files = foldersRepository.getFilesInFolder(folderId)
        Log.d("GetFilesUseCase", "Fetched files: $files for folderId: $folderId")
        return files

    }


}