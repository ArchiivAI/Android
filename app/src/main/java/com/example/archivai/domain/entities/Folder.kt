package com.example.archivai.domain.entities

data class Folder(
    val folderId: Int,
    val lastModifiedDate: String,
    val name: String,
    val numberOfEmployees: Int,
    val numberOfFiles: Int,
    val numberOfFolders: Int,
    val sectionName: String?,
    val size: Int
)