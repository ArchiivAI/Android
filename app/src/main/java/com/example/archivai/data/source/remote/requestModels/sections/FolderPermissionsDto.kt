package com.example.archivai.data.source.remote.requestModels.sections

data class FolderPermissionsDto(
    val filesActions: List<Int>,
    val folderActions: List<Int>,
    val subFoldersActions: List<Int>
)