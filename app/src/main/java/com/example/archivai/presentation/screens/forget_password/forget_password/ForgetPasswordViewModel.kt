package com.example.archivai.presentation.screens.forget_password.forget_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archivai.domain.usecases.authentication.SendChangePasswordMailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(
    private val sendChangePasswordMailUseCase: SendChangePasswordMailUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<ForgetPasswordUiState>(ForgetPasswordUiState.Idle)
    val uiState: StateFlow<ForgetPasswordUiState> = _uiState.asStateFlow()


    fun sendChangePasswordMail(email: String) {
        if (!email.matches(Regex("^[A-Za-z0-9+_.-]+@(.+)$"))) {
            _uiState.value = ForgetPasswordUiState.Error("Invalid email format")
            return
        }
        viewModelScope.launch {
            _uiState.value = ForgetPasswordUiState.Loading
            try {
                val result = sendChangePasswordMailUseCase(email)

                _uiState.value = ForgetPasswordUiState.Success("Otp Sent Successfully")
            } catch (e: Exception) {
                _uiState.value =
                    ForgetPasswordUiState.Error(e.message ?: "An unexpected error occurred")
            }

        }


    }


}