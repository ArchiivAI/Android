package com.example.archivai.data.source.remote.responseModels.roles

data class GetMissingUsersInRoleResponseItem(
    val email: String,
    val firstName: String,
    val id: Int,
    val imageUrl: String,
    val lastName: String,
    val passwordHashed: String,
    val positionModelResponse: PositionModelResponse
)