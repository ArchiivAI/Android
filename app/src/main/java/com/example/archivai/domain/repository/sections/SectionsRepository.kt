package com.example.archivai.domain.repository.sections

import com.example.archivai.data.source.remote.requestModels.sections.CreateSectionRequestModel
import com.example.archivai.data.source.remote.requestModels.sections.RenameRequestModel
import com.example.archivai.data.source.remote.responseModels.sections.CreateSectionResponseModel
import com.example.archivai.data.source.remote.responseModels.sections.DeleteSectionResponseModel
import com.example.archivai.data.source.remote.responseModels.sections.GetSectionDetailsResponseModel
import com.example.archivai.data.source.remote.responseModels.sections.RenameSectionResponseModel
import com.example.archivai.domain.entities.Employee
import com.example.archivai.domain.entities.Role
import com.example.archivai.domain.entities.Section

interface SectionsRepository {

    suspend fun getSections(): List<Section>

    suspend fun renameSection(sectionId: Int, newName: String): Result<Unit>

    suspend fun deleteSection(sectionId: Int): Result<Unit>

    suspend fun createSection(name: String): Result<Unit>

  //  suspend fun getSectionDetails(sectionId: Int): Result<Section>

  //  suspend fun getSectionRoles(sectionId: Int): Result<List<Role>>

   // suspend fun getSectionEmployees(sectionId: Int): List<Employee>

    //suspend fun getSectionRolesPermissions(roleId: Int, sectionId: Int): List<>

    suspend fun updateRoleInSectionPermissions(
        roleId: Int,
        sectionId: Int,
        permissions: List<String>
    ): Boolean




}