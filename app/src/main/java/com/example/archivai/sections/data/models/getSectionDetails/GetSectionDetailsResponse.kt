package com.example.archivai.sections.data.models.getSectionDetails

data class GetSectionDetailsResponse(
    val folders: Any,
    val foldersCount: Int,
    val id: Int,
    val lastModified: String,
    val name: String,
    val size: Int
)