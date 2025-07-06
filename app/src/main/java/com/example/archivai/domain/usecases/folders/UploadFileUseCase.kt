package com.example.archivai.domain.usecases.folders

import android.util.Log
import com.example.archivai.domain.repository.folders.FoldersRepository
import java.io.File
import javax.inject.Inject

class UploadFileUseCase @Inject constructor(
    private val repository: FoldersRepository
) {
    suspend operator fun invoke(folderId: Int, file: File): Result<Unit> {
        return try {
            Log.d("UploadFileUseCase", "Uploading file: ${file.name} to folder ID: $folderId")
            repository.uploadFile(folderId, file)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}