package com.example.archivai.presentation.screens.forget_password.forget_password

sealed class ForgetPasswordUiState {
    object Idle : ForgetPasswordUiState()
    object Loading : ForgetPasswordUiState()
    data class Success(val message: String) : ForgetPasswordUiState()
    data class Error(val message: String) : ForgetPasswordUiState()
}