package com.example.archivai.folders.data.models.createSubFolder

data class CreateSubFolderRequest(
    val folderName: String,
    val parentFolderId: Int
)