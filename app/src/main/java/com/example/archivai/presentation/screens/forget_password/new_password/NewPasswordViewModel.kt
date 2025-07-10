package com.example.archivai.presentation.screens.forget_password.new_password

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archivai.domain.usecases.authentication.CreateNewPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewPasswordViewModel @Inject constructor(private val newPasswordUseCase: CreateNewPasswordUseCase) :
    ViewModel() {
    private val _uiState = MutableStateFlow<NewPasswordUiState>(NewPasswordUiState.Idle)
    val uiState: StateFlow<NewPasswordUiState> = _uiState.asStateFlow()

    fun createNewPassword(
        newPassword: String,
        passwordConfirm: String,
        email: String,
        otp: String
    ) {
        _uiState.value = NewPasswordUiState.Loading
        if (newPassword != passwordConfirm) {
            _uiState.value = NewPasswordUiState.Error("Passwords do not match")
            return
        }
        viewModelScope.launch {
            Log.d("NewPasswordViewModel", "Creating $newPassword for email: $email with OTP: $otp")
            newPasswordUseCase.invoke(newPassword, email, otp)

                .onSuccess {
                    _uiState.value = NewPasswordUiState.Success("Password Changed Successfully")
                }
                .onFailure { e ->
                    _uiState.value =
                        NewPasswordUiState.Error(e.message ?: "An unexpected error occurred")
                }
        }

    }

}