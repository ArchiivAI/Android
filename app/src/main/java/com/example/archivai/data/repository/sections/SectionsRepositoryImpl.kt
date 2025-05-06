package com.example.archivai.data.repository.sections

import com.example.archivai.data.mappers.toDomain
import com.example.archivai.data.source.remote.requestModels.sections.CreateSectionRequestModel
import com.example.archivai.data.source.remote.requestModels.sections.RenameRequestModel
import com.example.archivai.data.source.remote.responseModels.sections.CreateSectionResponseModel
import com.example.archivai.data.source.remote.responseModels.sections.DeleteSectionResponseModel
import com.example.archivai.data.source.remote.responseModels.sections.GetSectionDetailsResponseModel
import com.example.archivai.data.source.remote.responseModels.sections.RenameSectionResponseModel
import com.example.archivai.data.source.remote.endpoint.sections.SectionsApiService
import com.example.archivai.domain.entities.Section
import com.example.archivai.domain.repository.sections.SectionsRepository
import javax.inject.Inject

class SectionsRepositoryImpl @Inject constructor  (private val apiService: SectionsApiService) :
    SectionsRepository {


    override suspend fun getSections(): List<Section> {
        return apiService.getSections(token = "Bearer <token>", page = 1)
            .map { it.toDomain() }
    }

    override suspend fun renameSection(
        token: String,
        sectionId: Int,
        request: RenameRequestModel
    )  : RenameSectionResponseModel {
       return apiService.renameSection(token,sectionId,request)
    }

    override suspend fun deleteSection(sectionId: Int) {
        return apiService.deleteSections(token = "Bearer <Token>",sectionId)
    }

    override suspend fun postSection(
        token: String,
        request: CreateSectionRequestModel
    ) : CreateSectionResponseModel {
        return apiService.createSection(token,request)
    }

    override suspend fun getSectionDetails(
        token: String,
        sectionId: Int
    ): GetSectionDetailsResponseModel {
        return apiService.getSectionDetails(token, sectionId)
    }


}