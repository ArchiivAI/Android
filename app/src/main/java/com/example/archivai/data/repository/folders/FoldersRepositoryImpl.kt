package com.example.archivai.data.repository.folders

import android.util.Log
import com.example.archivai.data.source.remote.endpoint.folder.FoldersApiService
import com.example.archivai.data.utils.SharedPrefsHelper
import com.example.archivai.domain.entities.Employee
import com.example.archivai.domain.entities.Folder
import com.example.archivai.domain.entities.Role
import com.example.archivai.domain.repository.folders.FoldersRepository
import javax.inject.Inject

class FoldersRepositoryImpl @Inject constructor(
    private val apiService: FoldersApiService
) : FoldersRepository{
    val token =  "Bearer ${SharedPrefsHelper.getToken()}"
    override suspend fun getFoldersInSection(sectionId: Int): List<Folder> {
       val folders = return apiService.getFoldersInSection(
            token,
            sectionId,
            page = 1
        ).toDomainList()
        return folders
        Log.d("Folders Fetched", "Fetched folders: $folders")
        Log.d("FoldersRepositoryImpl", "Fetched folders: ${apiService.getFoldersInSection(token, sectionId, 1)}")

    }

    override suspend fun createFolderInSection(
        sectionId: Int,
        folderName: String
    ): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun renameFolder(
        name: String,
        folderId: Int
    ): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFolder(folderId: Int): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getFolderRoles(folderId: Int): Result<List<Role>> {
        TODO("Not yet implemented")
    }

    override suspend fun getFolderEmployees(folderId: Int): Result<List<Employee>> {
        TODO("Not yet implemented")
    }

}