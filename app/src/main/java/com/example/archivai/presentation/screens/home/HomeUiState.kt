package com.example.archivai.presentation.screens.home

import com.example.archivai.domain.entities.QuickAccessSection
import com.example.archivai.domain.entities.Section

data class HomeUiState (
    val isLoading : Boolean = false,
    val error: String? = null,
    val storageUsed: String = "8",
    val totalStorage: String = "6",
    val userName : String = "",
    val wordPercentage: Float = 0f,
    val imagePercentage: Float= 0f,
    val excelPercentage: Float= 0f,
    val pdfPercentage: Float= 0f,
    val imageUrl : String? = null,
    val quickAccessSections : List<QuickAccessSection>? = emptyList()
)