package com.example.archivai.data.source.remote.requestModels.employees

data class AddEmployeeRequest(
    val email: String,
    val firstName: String,
    val lastName: String,
    val roles: List<Int>
)