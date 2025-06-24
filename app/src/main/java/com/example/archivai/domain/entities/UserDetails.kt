package com.example.archivai.domain.entities

import com.example.archivai.data.source.remote.responseModels.home.Position

data class UserDetails(
    val email: String,
    val firstName: String,
    val id: Int,
    val imageUrl: String,
    val lastName: String
)
