package com.example.archivai.employees.data.models.getEmployees

data class Employee(
    val email: String,
    val firstName: String,
    val id: Int,
    val imageUrl: Any,
    val lastName: String,
    val passwordHashed: String,
    val position: Position
)