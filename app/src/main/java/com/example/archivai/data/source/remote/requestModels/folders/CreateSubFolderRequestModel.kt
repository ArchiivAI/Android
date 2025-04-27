package com.example.archivai.data.source.remote.requestModels.folders

data class CreateSubFolderRequestModel(
    val folderName: String,
    val parentFolderId: Int
)