package com.example.archivai.data.source.remote.responseModels.home

import com.example.archivai.domain.entities.QuickAccessSection

data class QuickAccessSectionsResponseModel(
    val files: List<QuickFile>,
    val folders: List<QuickFolder>,
    val sections: List<QuickSection>
){
    fun toQuickAccessSections(): List<QuickAccessSection> {
        return sections.map { section ->
            QuickAccessSection(
                name = section.name,
                dateTime = section.dateTime,
                entityType = section.entityType,
                entityId = section.entityId

            )
        }
    }
}