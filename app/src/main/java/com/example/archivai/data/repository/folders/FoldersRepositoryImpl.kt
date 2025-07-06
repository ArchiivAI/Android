package com.example.archivai.data.repository.folders

import android.util.Log
import com.example.archivai.data.source.remote.endpoint.folder.FoldersApiService
import com.example.archivai.data.source.remote.requestModels.folders.CreateFolderRequestModel
import com.example.archivai.data.source.remote.requestModels.folders.CreateSubFolderRequestModel
import com.example.archivai.data.source.remote.requestModels.folders.RenameFolderRequestModel
import com.example.archivai.data.utils.SharedPrefsHelper
import com.example.archivai.domain.entities.Employee
import com.example.archivai.domain.entities.Folder
import com.example.archivai.domain.entities.Role
import com.example.archivai.domain.repository.folders.FoldersRepository
import com.example.archivai.domain.entities.File
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import javax.inject.Inject
import kotlin.io.extension

class FoldersRepositoryImpl @Inject constructor(
    private val apiService: FoldersApiService
) : FoldersRepository {
    val token = "Bearer ${SharedPrefsHelper.getToken()}"
    override suspend fun getFoldersInSection(sectionId: Int): List<Folder> {
        val folders = return apiService.getFoldersInSection(
            token,
            sectionId,
            page = 1
        ).toDomainList()
        return folders
        Log.d("Folders Fetched", "Fetched folders: $folders")
        Log.d(
            "FoldersRepositoryImpl",
            "Fetched folders: ${apiService.getFoldersInSection(token, sectionId, 1)}"
        )

    }

    override suspend fun getFoldersInFolder(folderId: Int): List<Folder> {
        val folders = apiService.getSubFolders(
            token,
            folderId,
            page = 1
        ).toDomainList()
        return folders
        Log.d("Folders Fetched", "Fetched folders: ${folders.size}")
        Log.d(
            "FoldersRepositoryImpl",
            "Fetched folders: ${apiService.getFoldersInSection(token, folderId, 1)}"
        )
    }

    override suspend fun getFilesInFolder(folderId: Int): List<File> {
        Log.d("FilesRepo", " ENTERED getFilesInFolder for folderId=$folderId") // Add this first

        return try {
            Log.d("FilesRepo", " Using token: ${token.take(5)}...")
            val response = apiService.getFiles(token, folderId, page = 1)
            Log.d("FilesRepo", " Raw API response: $response")

            val files = response.toDomainList()
            Log.d("FilesRepo", " Fetched ${files.size} files")
            files
        } catch (e: Exception) {
            Log.e("FilesRepo", " Error fetching files", e)
            emptyList()
        }
    }

    override suspend fun uploadFile(folderId: Int, file: java.io.File): Result<Unit> {
        return try {
            // Detect MIME type
            val mimeType = file.getMimeType()
            val requestBody = file.asRequestBody(mimeType.toMediaTypeOrNull())

            // Create multipart part
            val filePart = MultipartBody.Part.createFormData(
                name = "formFiles", // <-- change to "file" if backend expects that
                filename = file.name,
                body = requestBody
            )

            Log.d("UploadFile", "Uploading file: ${file.name} with MIME type: $mimeType to folder ID: $folderId")

            // Call the API
            val response = apiService.uploadFile(token,folderId, filePart)

            // Handle the result
            if (response.isSuccessful) {
                Log.d("UploadFile", "Upload successful for ${file.name}")
                Result.success(Unit)
            } else {
                val code = response.code()
                val message = response.message()
                val errorBody = response.errorBody()?.string()

                Log.e("UploadFile", "Upload failed - Code: $code, Message: $message, ErrorBody: $errorBody")

                Result.failure(Exception("Upload failed - Code: $code, Message: $message"))
            }
        } catch (e: Exception) {
            Log.e("UploadFile", "Exception during upload: ${e.message}", e)
            Result.failure(e)
        }
    }

    private fun java.io.File.getMimeType(): String {
        return when (extension.lowercase()) {
            "jpg", "jpeg" -> "image/jpeg"
            "png" -> "image/png"
            "pdf" -> "application/pdf"
            "doc", "docx" -> "application/msword"
            "xls", "xlsx" -> "application/vnd.ms-excel"
            "csv" -> "text/csv"
            else -> "application/octet-stream"
        }
    }


    override suspend fun createSubFolderInFolder(
        folderId: Int,
        folderName: String
    ): Result<Unit> {
        try {
            val createSubFolderRequestModel = CreateSubFolderRequestModel(
                folderName = folderName,
                parentFolderId = folderId
            )
            val response = apiService.createSubFolder(token, createSubFolderRequestModel)
            Log.d("repo", folderName)
            return if (response.message.contains("Folder created successfully")) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to create folder"))
            }
        } catch (e: Exception) {
            Log.e("FoldersRepository", "Error creating folder: ${e.message}")
            return Result.failure(e)
        }
    }

    override suspend fun createFolderInSection(
        sectionId: Int,
        folderName: String
    ): Result<Unit> {
        try {
            val createFolderRequestModel = CreateFolderRequestModel(
                folderName = folderName,
                sectionId = sectionId
            )
            val response = apiService.createFolder(token, createFolderRequestModel)
            Log.d("repo", folderName)
            return if (response.message.contains("Folder created successfully")) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to create folder"))
            }
        } catch (e: Exception) {
            Log.e("FoldersRepository", "Error creating folder: ${e.message}")
            return Result.failure(e)
        }
    }


    override suspend fun renameFolder(
        name: String,
        folderId: Int
    ): Result<Unit> {
        try {
            val renameFolderRequestModel = RenameFolderRequestModel(name)
            val response = apiService.renameFolder(token, folderId, renameFolderRequestModel)
            return if (response.message.contains("Folder renamed successfully")) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to rename folder"))
            }
        } catch (e: Exception) {
            Log.e("FoldersRepository", "Error renaming folder: ${e.message}")
            return Result.failure(e)
        }
    }

    override suspend fun deleteFolder(folderId: Int): Result<Unit> {
        try {
            val response = apiService.deleteFolder(token, folderId)
            return if (response.message.contains("Folder Deleted successfully")) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to delete Folder"))
            }
        } catch (e: Exception) {
            Log.e("FoldersRepository", "Error deleting folder: ${e.message}")
            return Result.failure(e)
        }
    }

    override suspend fun getFolderRoles(folderId: Int): Result<List<Role>> {
        TODO("Not yet implemented")
    }

    override suspend fun getFolderEmployees(folderId: Int): Result<List<Employee>> {
        TODO("Not yet implemented")
    }

}