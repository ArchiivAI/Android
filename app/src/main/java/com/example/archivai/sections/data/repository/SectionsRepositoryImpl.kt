package com.example.archivai.sections.data.repository

import com.example.archivai.sections.data.SectionsApiService
import com.example.archivai.sections.data.models.Section
import com.example.archivai.sections.data.models.createSection.CreateSectionRequest
import com.example.archivai.sections.data.models.createSection.CreateSectionResponse
import com.example.archivai.sections.data.models.deleteSection.DeleteSectionResponse
import com.example.archivai.sections.data.models.getSectionDetails.GetSectionDetailsResponse
import com.example.archivai.sections.data.models.renameSection.RenameRequest
import com.example.archivai.sections.data.models.renameSection.RenameSectionResponse
import com.example.archivai.sections.domain.SectionsRepository
import javax.inject.Inject

class SectionsRepositoryImpl @Inject constructor  (private val apiService: SectionsApiService) : SectionsRepository {


    override suspend fun getSections(token : String,page: Int): List<Section> {
        return apiService.getSections(token ,page)
    }

    override suspend fun renameSection(
        token: String,
        sectionId: Int,
        request: RenameRequest
    )  : RenameSectionResponse{
       return apiService.renameSection(token,sectionId,request)
    }

    override suspend fun deleteSection(token: String, sectionId: Int) : DeleteSectionResponse{
        return apiService.deleteSections(token,sectionId)
    }

    override suspend fun postSection(
        token: String,
        request: CreateSectionRequest
    ) : CreateSectionResponse {
        return apiService.createSection(token,request)
    }

    override suspend fun getSectionDetails(
        token: String,
        sectionId: Int
    ): GetSectionDetailsResponse {
        return apiService.getSectionDetails(token, sectionId)
    }


}