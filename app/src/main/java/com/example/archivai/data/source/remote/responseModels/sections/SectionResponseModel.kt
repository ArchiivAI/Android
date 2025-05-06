package com.example.archivai.data.source.remote.responseModels.sections

data class SectionResponseModel(
    val id: Int,
    val lastModified: String,
    val name: String,
    val numberOfEmployees: Int,
    val numberOfFolders: Int,
    val size: Int
)