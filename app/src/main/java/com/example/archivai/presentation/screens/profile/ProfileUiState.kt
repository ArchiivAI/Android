package com.example.archivai.presentation.screens.profile

data class ProfileUiState(
    val showLogOutDialog : Boolean = false,
    val isLoggingOut : Boolean = false,
    val imageUrl : String? = null,
    val isLoading : Boolean = false,
    val error: String? = null,
    val userName : String = ""
)