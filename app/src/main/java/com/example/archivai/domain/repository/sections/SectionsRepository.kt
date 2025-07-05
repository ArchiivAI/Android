package com.example.archivai.domain.repository.sections

import com.example.archivai.domain.entities.Section

interface SectionsRepository {

    suspend fun getSections(): List<Section>

    suspend fun renameSection(sectionId: Int, newName: String): Result<Unit>

    suspend fun deleteSection(sectionId: Int): Result<Unit>

    suspend fun createSection(name: String): Result<Unit>

    suspend fun getRolePermissionsInSection(
        sectionId: Int,
        roleId: Int
    ): List<Int>

    suspend fun updateRoleSectionPermissions(
        roleId : Int,
        sectionId: Int,
        sectionActions: List<Int>
    ): Result<Unit>

    suspend fun getUserSectionPermissions(
        userId: Int,
        sectionId: Int
    ): List<Int>

    suspend fun updateUserSectionPermissions(
        userId: Int,
        sectionId: Int,
        sectionActions: List<Int>
    ): Result<Unit>




}