package com.example.archivai.data.mappers

import com.example.archivai.data.source.remote.responseModels.roles.RoleModelResponse
import com.example.archivai.domain.entities.Role

fun RoleModelResponse.toDomain() : Role{
    return Role(
        id = id,
        name = name
    )
}