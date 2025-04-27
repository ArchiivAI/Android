package com.example.archivai.data.source.remote.responseModels.folders

data class FolderDetailsResponseModel(
    val folderId : Int,
    val name : String,
    val sectionName : String,
    val size : Int,
    val lastModifiedDate : String,
    val numberOfEmployees : Int,
    val numberOfFiles : Int,
    val numberOfFolders : Int
)
