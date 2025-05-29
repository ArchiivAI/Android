package com.example.archivai.presentation.screens.forget_password.otp_verify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archivai.domain.usecases.authentication.VerifyOtpUseCase
import com.example.archivai.presentation.screens.forget_password.forget_password.ForgetPasswordUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpVerifyViewModel @Inject constructor(private val otpUseCase: VerifyOtpUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<OtpVerifyUiState>(OtpVerifyUiState.Idle)
    val uiState : StateFlow<OtpVerifyUiState> = _uiState.asStateFlow()

    fun verifyOtp(otp: String , email : String) {
        _uiState.value = OtpVerifyUiState.Loading
        viewModelScope.launch {
            try {
                val result = otpUseCase.invoke(otp, email)
                _uiState.value = OtpVerifyUiState.Success("Otp Confirmed Successfully")
            } catch (e: Exception) {
                _uiState.value =
                    OtpVerifyUiState.Error(e.message ?: "An unexpected error occurred")
            }

        }
    }
}