package com.example.archivai.domain.repository.folders

import com.example.archivai.domain.entities.Employee
import com.example.archivai.domain.entities.File
import com.example.archivai.domain.entities.Folder
import com.example.archivai.domain.entities.Role

interface FoldersRepository {
    suspend fun getFoldersInSection(sectionId: Int): List<Folder>

    suspend fun getFoldersInFolder(folderId: Int): List<Folder>

    suspend fun createSubFolderInFolder(
        folderId: Int,
        folderName: String
    ): Result<Unit>

    suspend fun createFolderInSection(sectionId: Int, folderName: String): Result<Unit>

    suspend fun getFilesInFolder(folderId: Int): List<File>

    suspend fun uploadFile(folderId: Int, file: java.io.File): Result<Unit>


    suspend fun renameFolder(name : String , folderId : Int): Result<Unit>

    suspend fun deleteFolder(folderId: Int): Result<Unit>

    suspend fun getFolderRoles(folderId: Int): Result<List<Role>>

    suspend fun getFolderEmployees(folderId: Int): Result<List<Employee>>



}