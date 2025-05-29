package com.example.archivai.data.source.remote.responseModels.home

import com.example.archivai.domain.entities.StorageDoc

data class GetDocsResponseModel(
    val csvCount: Int,
    val excelCount: Int,
    val filesCount: Int,
    val id: Int,
    val imageCount: Int,
    val pdfCount: Int,
    val wordCount: Int
){
    fun toDocsResponse(): StorageDoc{
        return StorageDoc(
            excelCount = excelCount,
            imageCount = imageCount,
            pdfCount = pdfCount,
            wordCount = wordCount
        )
    }

}
