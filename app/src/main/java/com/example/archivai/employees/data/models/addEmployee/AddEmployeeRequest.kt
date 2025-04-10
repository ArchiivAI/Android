package com.example.archivai.employees.data.models.addEmployee

data class AddEmployeeRequest(
    val email: String,
    val firstName: String,
    val lastName: String,
    val roles: List<Int>
)