package com.example.archivai.data.source.remote.responseModels.sections

import com.example.archivai.domain.entities.Section

data class GetSectionDetailsResponseModel(
    val folders: Any,
    val foldersCount: Int,
    val id: Int,
    val lastModified: String,
    val name: String,
    val size: Int
) {
    fun toSection() = Section(
        id = id,
        name = name,
        size = size,
        lastModified = lastModified,
    )
}