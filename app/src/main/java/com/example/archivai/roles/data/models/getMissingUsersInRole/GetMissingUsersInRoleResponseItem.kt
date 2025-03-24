package com.example.archivai.roles.data.models.getMissingUsersInRole

data class GetMissingUsersInRoleResponseItem(
    val email: String,
    val firstName: String,
    val id: Int,
    val imageUrl: String,
    val lastName: String,
    val passwordHashed: String,
    val position: Position
)