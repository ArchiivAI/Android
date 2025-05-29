package com.example.archivai.domain.entities

data class Folder(
    val folderId: Int,
    val lastModifiedDate: String,
    val name: String,
    val numberOfEmployees: Int,
    val numberOfFiles: Int,
    val numberOfFolders: Int,
    val files : List<File>? = null,
    val sectionName: Any,
    val size: Int
)