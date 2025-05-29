package com.example.archivai.data.mappers

import com.example.archivai.data.source.remote.responseModels.employees.EmployeeResponseModel
import com.example.archivai.domain.entities.Employee

fun EmployeeResponseModel.toDomainModel(): Employee {
    return Employee(
        email = email,
        firstName = firstName,
        id = id,
        imageUrl = imageUrl,
        lastName = lastName
    )
}