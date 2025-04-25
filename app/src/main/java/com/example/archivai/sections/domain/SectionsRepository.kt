package com.example.archivai.sections.domain

import com.example.archivai.sections.data.models.Section
import com.example.archivai.sections.data.models.createSection.CreateSectionRequest
import com.example.archivai.sections.data.models.createSection.CreateSectionResponse
import com.example.archivai.sections.data.models.deleteSection.DeleteSectionResponse
import com.example.archivai.sections.data.models.getSectionDetails.GetSectionDetailsResponse
import com.example.archivai.sections.data.models.renameSection.RenameRequest
import com.example.archivai.sections.data.models.renameSection.RenameSectionResponse

interface SectionsRepository {

    suspend fun getSections(token : String , page : Int) : List<Section>

    suspend fun renameSection(token : String, sectionId: Int , request: RenameRequest ) : RenameSectionResponse

    suspend fun deleteSection(token : String , sectionId: Int) : DeleteSectionResponse

    suspend fun postSection(token: String , request: CreateSectionRequest) : CreateSectionResponse

    suspend fun getSectionDetails(token: String , sectionId: Int) : GetSectionDetailsResponse








}