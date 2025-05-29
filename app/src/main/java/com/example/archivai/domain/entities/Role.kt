package com.example.archivai.domain.entities

data class Role(
    val id: Int,
    val name: String,
    val description: String,
    val permissions: List<String>)
