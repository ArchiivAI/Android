package com.example.archivai.presentation.screens.contact_us

sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Success(val message: String) : UiState()
    data class Error(val error: String) : UiState()


}