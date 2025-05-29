package com.example.archivai.domain.entities

data class Employee(
    val id: Int,
    val name: String,
    val surname: String,
    val email: String,
    val phoneNumber: String,
    val position: String,
    val sectionId: Int,
    val sectionName: String,
    val sectionSize: Long,
    val sectionLastModified: String
)
