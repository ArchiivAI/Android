package com.example.archivai.domain.repository.sections

import com.example.archivai.data.source.remote.requestModels.sections.CreateSectionRequestModel
import com.example.archivai.data.source.remote.requestModels.sections.RenameRequestModel
import com.example.archivai.data.source.remote.responseModels.sections.CreateSectionResponseModel
import com.example.archivai.data.source.remote.responseModels.sections.DeleteSectionResponseModel
import com.example.archivai.data.source.remote.responseModels.sections.GetSectionDetailsResponseModel
import com.example.archivai.data.source.remote.responseModels.sections.RenameSectionResponseModel
import com.example.archivai.data.source.remote.responseModels.sections.SectionResponseModel

interface SectionsRepository {

    suspend fun getSections(token : String , page : Int) : List<SectionResponseModel>

    suspend fun renameSection(token : String, sectionId: Int , request: RenameRequestModel) : RenameSectionResponseModel

    suspend fun deleteSection(token : String , sectionId: Int) : DeleteSectionResponseModel

    suspend fun postSection(token: String , request: CreateSectionRequestModel) : CreateSectionResponseModel

    suspend fun getSectionDetails(token: String , sectionId: Int) : GetSectionDetailsResponseModel








}