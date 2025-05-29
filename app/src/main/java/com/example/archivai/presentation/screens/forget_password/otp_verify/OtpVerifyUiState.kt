package com.example.archivai.presentation.screens.forget_password.otp_verify

sealed class OtpVerifyUiState {
    data object Idle : OtpVerifyUiState()
    data object Loading : OtpVerifyUiState()
    data class Success(val message: String) : OtpVerifyUiState()
    data class Error(val message: String) : OtpVerifyUiState()


}