package com.example.archivai.data.source.remote.responseModels.sections

data class GetSectionDetailsResponseModel(
    val folders: Any,
    val foldersCount: Int,
    val id: Int,
    val lastModified: String,
    val name: String,
    val size: Int
)