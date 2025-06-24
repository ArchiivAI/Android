package com.example.archivai.domain.repository.folders

import com.example.archivai.domain.entities.Employee
import com.example.archivai.domain.entities.Folder
import com.example.archivai.domain.entities.Role

interface FoldersRepository {
    suspend fun getFoldersInSection(sectionId: Int): List<Folder>

    suspend fun createFolderInSection(sectionId: Int, folderName: String): Result<Unit>

    suspend fun renameFolder(name : String , folderId : Int): Result<Unit>

    suspend fun deleteFolder(folderId: Int): Result<Unit>

    suspend fun getFolderRoles(folderId: Int): Result<List<Role>>

    suspend fun getFolderEmployees(folderId: Int): Result<List<Employee>>

}