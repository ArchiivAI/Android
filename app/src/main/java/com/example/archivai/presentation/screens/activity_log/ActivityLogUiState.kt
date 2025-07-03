package com.example.archivai.presentation.screens.activity_log

import com.example.archivai.domain.entities.ActivityLog

data class ActivityLogUiState(

    val logs : List<ActivityLog> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)