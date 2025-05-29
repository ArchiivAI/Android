package com.example.archivai.data.repository.sections

import android.util.Log
import com.example.archivai.data.mappers.toDomain
import com.example.archivai.data.source.remote.requestModels.sections.CreateSectionRequestModel
import com.example.archivai.data.source.remote.requestModels.sections.RenameRequestModel
import com.example.archivai.data.source.remote.responseModels.sections.CreateSectionResponseModel
import com.example.archivai.data.source.remote.responseModels.sections.DeleteSectionResponseModel
import com.example.archivai.data.source.remote.responseModels.sections.GetSectionDetailsResponseModel
import com.example.archivai.data.source.remote.responseModels.sections.RenameSectionResponseModel
import com.example.archivai.data.source.remote.endpoint.sections.SectionsApiService
import com.example.archivai.data.utils.SharedPrefsHelper
import com.example.archivai.domain.entities.Section
import com.example.archivai.domain.repository.sections.SectionsRepository
import javax.inject.Inject

class SectionsRepositoryImpl @Inject constructor(private val apiService: SectionsApiService) : SectionsRepository {
    val token =  "Bearer ${SharedPrefsHelper.getToken()}"
    override suspend fun getSections(): List<Section> {
        return apiService.getSections(token)
            .map { it.toDomain() }
    }

    override suspend fun renameSection(
        sectionId: Int,
        newName: String
    ): Result<Unit> {
        try {
            val renameRequestModel = RenameRequestModel(newName)
            val response = apiService.renameSection(token,sectionId,renameRequestModel)
            return if (response.message.contains("Section renamed successfully")){
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to rename section"))
            }
        }catch (e: Exception){
            Log.e("SectionsRepository", "Error renaming section: ${e.message}")
            return Result.failure(e)
        }
    }

    override suspend fun deleteSection(sectionId: Int): Result<Unit> {
        try {
            val response = apiService.deleteSections(token,sectionId)
            return if (response.message.contains("Section deleted successfully")){
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to delete section"))
            }
        }catch (e: Exception){
            Log.e("SectionsRepository", "Error deleting section: ${e.message}")
            return Result.failure(e)
        }

    }

    override suspend fun createSection(name: String): Result<Unit> {
        try {
            val createSectionRequestModel = CreateSectionRequestModel(name)
            val response = apiService.createSection(token,createSectionRequestModel)
            Log.d("repo",name)
            return if (response.message.contains("Section created successfully")){
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to create section"))
            }
        }catch (e: Exception){
            Log.e("SectionsRepository", "Error creating section: ${e.message}")
            return Result.failure(e)
        }
    }

    override suspend fun updateRoleInSectionPermissions(
        roleId: Int,
        sectionId: Int,
        permissions: List<String>
    ): Boolean {
        TODO("Not yet implemented")
    }
}