package com.example.archivai.domain.entities

data class Section(
    val id: Int,
    val name: String,
    val size: Int,
    val lastModified: String,
    val numberOfFolders: Int? = null,
    val numberOfEmployees: Int? = null,
    val folders: List<Folder>? = null,
    val foldersCount: Int? = null
)