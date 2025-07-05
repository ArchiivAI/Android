package com.example.archivai.data.utils

import com.example.archivai.domain.models.sections.SectionAction

fun mapToSectionActions(indices: List<Int>): List<SectionAction> {
    return indices.mapNotNull { index ->
        SectionAction.values().getOrNull(index)
    }
}