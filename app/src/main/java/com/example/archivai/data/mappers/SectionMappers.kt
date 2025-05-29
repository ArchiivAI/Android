package com.example.archivai.data.mappers

import com.example.archivai.data.source.remote.responseModels.sections.SectionResponseModel
import com.example.archivai.domain.entities.Section

fun SectionResponseModel.toDomain(): Section {
    return Section(
        id = id,
        name = name,
        size = size,
        lastModified = lastModified,
        numberOfFolders = numberOfFolders,
        numberOfEmployees = numberOfEmployees
    )
}

