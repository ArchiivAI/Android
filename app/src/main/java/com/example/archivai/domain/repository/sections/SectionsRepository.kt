package com.example.archivai.domain.repository.sections

import com.example.archivai.data.source.remote.requestModels.sections.CreateSectionRequestModel
import com.example.archivai.data.source.remote.requestModels.sections.RenameRequestModel
import com.example.archivai.data.source.remote.responseModels.sections.CreateSectionResponseModel
import com.example.archivai.data.source.remote.responseModels.sections.DeleteSectionResponseModel
import com.example.archivai.data.source.remote.responseModels.sections.GetSectionDetailsResponseModel
import com.example.archivai.data.source.remote.responseModels.sections.RenameSectionResponseModel
import com.example.archivai.domain.entities.Section

interface SectionsRepository {

    suspend fun getSections(): List<Section>

    suspend fun renameSection(sectionId: Int, newName : String) : Section

    suspend fun deleteSection(sectionId: Int)

    suspend fun createSection(name : String): Section

    suspend fun getSectionDetails(sectionId: Int): Section


}