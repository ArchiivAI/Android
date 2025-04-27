package com.example.archivai.data.source.remote.responseModels.sections

data class SectionResponseModel(
    val id : Int ,
    val name : String,
    val size : Int,
    val lastModified : String,
    val numberOfFolders : Int,
    val numberOfEmployees : Int
)