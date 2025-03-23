package com.example.archivai.folders.data.models.getFoldersDetailsInSection

data class FolderDetails(
    val folderId : Int,
    val name : String,
    val sectionName : String,
    val size : Int,
    val lastModifiedDate : String,
    val numberOfEmployees : Int,
    val numberOfFiles : Int,
    val numberOfFolders : Int
)
