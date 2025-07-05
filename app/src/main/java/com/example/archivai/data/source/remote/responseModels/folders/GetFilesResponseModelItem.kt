package com.example.archivai.data.source.remote.responseModels.folders

data class GetFilesResponseModelItem(
    val createdBy: Int,
    val currentVersion: Int,
    val folderId: Int,
    val id: Int,
    val lastModifiedDate: String,
    val locked: Boolean,
    val lockedBy: Any,
    val metadata: Metadata,
    val name: String,
    val ocrText: List<Any>,
    val path: String,
    val relativePath: String,
    val size: Int,
    val type: Int,
    val uploadDate: String,
    val versions: List<Any>
)