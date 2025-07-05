package com.example.archivai.data.repository.sections

import android.util.Log
import com.example.archivai.data.mappers.toDomain
import com.example.archivai.data.source.remote.requestModels.sections.CreateSectionRequestModel
import com.example.archivai.data.source.remote.requestModels.sections.RenameRequestModel
import com.example.archivai.data.source.remote.endpoint.sections.SectionsApiService
import com.example.archivai.data.source.remote.requestModels.sections.SectionPermissionsDto
import com.example.archivai.data.source.remote.requestModels.sections.UpdateSectionPermissionsRequestModel
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

    override suspend fun getRolePermissionsInSection(
        sectionId: Int,
        roleId: Int
    ): List<Int> {
        try {
            val response =  apiService.getSectionPermissionsByRole(token, roleId, sectionId)
            Log.d("rolePermissions"," $response")
           return response
        } catch (e: Exception) {
            Log.e("SectionsRepository", "Error fetching role permissions in section: ${e.message}")
            return emptyList()
        }
    }

    override suspend fun updateRoleSectionPermissions(
        roleId: Int,
        sectionId: Int,
        sectionActions: List<Int>
    ): Result<Unit> {
        return try {
            val request = UpdateSectionPermissionsRequestModel(
                entityId = sectionId,
                roleId = roleId,
                sectionPermissionsDto = SectionPermissionsDto(
                    sectionActions = sectionActions
                ))
            Log.d("SectionRepositoryImpl", "Request to update role permissions: $request")

            val response = apiService.updateSectionPermissionsByRole(token, request)
            if (response.message.contains("Permissions updated successfully")) {
                Log.d("SectionsRepository", "Role permissions updated successfully in section $sectionId for role $roleId")
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to update role permissions"))
            }
        } catch (e: Exception) {
            Log.e("SectionsRepository", "Error updating role permissions in section: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun getUserSectionPermissions(
        userId: Int,
        sectionId: Int
    ): List<Int> {
        return try {
            val response = apiService.getSectionPermissionsByUser(token, userId, sectionId)
            response
        } catch (e: Exception) {
            Log.e("SectionsRepository", "Error fetching user $userId permissions in section $sectionId: ${e.message}")
            emptyList()
        }

    }

    override suspend fun updateUserSectionPermissions(
        userId: Int,
        sectionId: Int,
        sectionActions: List<Int>
    ): Result<Unit> {
        return try {
            val request = UpdateSectionPermissionsRequestModel(
                entityId = sectionId,
                userId = userId,
                sectionPermissionsDto = SectionPermissionsDto(
                    sectionActions = sectionActions
                ))
            Log.d("SectionRepositoryImpl", "Request to update user permissions: $request")
            val response = apiService.updateSectionPermissionsByUser(token, request)
            if (response.message.contains("Permissions updated successfully")) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to update role permissions"))
            }
        } catch (e: Exception) {
            Log.e("SectionsRepository", "Error updating user $userId permissions in section $sectionId: ${e.message}")
            Log.d("body","error body: ${e.localizedMessage}")
            Result.failure(e)
        }
    }

}