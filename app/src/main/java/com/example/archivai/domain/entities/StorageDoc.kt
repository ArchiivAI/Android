package com.example.archivai.domain.entities

data class StorageDoc(
    val excelCount: Int,
    val imageCount: Int,
    val pdfCount: Int,
    val wordCount: Int
)