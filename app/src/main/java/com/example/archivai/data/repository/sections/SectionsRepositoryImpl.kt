package com.example.archivai.data.repository.sections

import com.example.archivai.data.source.remote.requestModels.sections.CreateSectionRequestModel
import com.example.archivai.data.source.remote.requestModels.sections.RenameRequestModel
import com.example.archivai.data.source.remote.responseModels.sections.CreateSectionResponseModel
import com.example.archivai.data.source.remote.responseModels.sections.DeleteSectionResponseModel
import com.example.archivai.data.source.remote.responseModels.sections.GetSectionDetailsResponseModel
import com.example.archivai.data.source.remote.responseModels.sections.RenameSectionResponseModel
import com.example.archivai.data.source.remote.responseModels.sections.SectionResponseModel
import com.example.archivai.data.source.remote.endpoint.sections.SectionsApiService
import com.example.archivai.domain.repository.sections.SectionsRepository
import javax.inject.Inject

class SectionsRepositoryImpl @Inject constructor  (private val apiService: SectionsApiService) :
    SectionsRepository {


    override suspend fun getSections(token : String,page: Int): List<SectionResponseModel> {
        return apiService.getSections(token ,page)
    }

    override suspend fun renameSection(
        token: String,
        sectionId: Int,
        request: RenameRequestModel
    )  : RenameSectionResponseModel {
       return apiService.renameSection(token,sectionId,request)
    }

    override suspend fun deleteSection(token: String, sectionId: Int) : DeleteSectionResponseModel {
        return apiService.deleteSections(token,sectionId)
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