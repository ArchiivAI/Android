package com.example.archivai.domain.entities

data class Employee(
    val email: String,
    val firstName: String,
    val id: Int,
    val imageUrl: Any? = null,
    val lastName: String
)