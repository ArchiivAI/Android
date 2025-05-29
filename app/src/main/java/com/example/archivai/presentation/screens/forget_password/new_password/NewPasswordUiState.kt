package com.example.archivai.presentation.screens.forget_password.new_password

import com.example.archivai.presentation.screens.forget_password.forget_password.ForgetPasswordUiState

sealed class NewPasswordUiState {
    object Idle : NewPasswordUiState()
    object Loading : NewPasswordUiState()
    data class Success(val message: String) : NewPasswordUiState()
    data class Error(val message: String) : NewPasswordUiState()
}