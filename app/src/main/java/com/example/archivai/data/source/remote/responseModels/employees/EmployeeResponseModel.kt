package com.example.archivai.data.source.remote.responseModels.employees

data class EmployeeResponseModel(
    val email: String,
    val firstName: String,
    val id: Int,
    val imageUrl: Any,
    val lastName: String,
    val passwordHashed: String,
    val positionResponseModel: PositionResponseModel
)