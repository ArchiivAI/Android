package com.example.archivai.sections.data.models

data class Section(
    val id : Int ,
    val name : String,
    val size : Int,
    val lastModified : String,
    val numberOfFolders : Int,
    val numberOfEmployees : Int
)