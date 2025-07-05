package com.example.archivai.data.source.remote.requestModels.sections

data class UpdateSectionPermissionsRequestModel(
    val entityId: Int = 0,
    val filePermissionsDto: FilePermissionsDto = FilePermissionsDto(
        fileActions = listOf(0)
    ),
    val folderPermissionsDto: FolderPermissionsDto  = FolderPermissionsDto(
        folderActions = listOf(0),
        filesActions = listOf(0),
        subFoldersActions = listOf(0)
    ),
    val roleId: Int = 0,
    val sectionPermissionsDto: SectionPermissionsDto = SectionPermissionsDto(
        sectionActions = listOf(0)
    ),
    val userId: Int = 0
)