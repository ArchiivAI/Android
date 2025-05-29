package com.example.archivai.presentation.screens.login_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archivai.domain.usecases.authentication.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    var uiState by mutableStateOf(LoginUiState())
        private set


    fun onUsernameChanged(username: String) {
        uiState = uiState.copy(username = username, errorMessage = null)
    }

    fun onRememberMeChanged(rememberMe: Boolean) {
        uiState = uiState.copy(rememberMe = rememberMe, errorMessage = null)
    }

    fun onPasswordChanged(password: String) {
        uiState = uiState.copy(password = password, errorMessage = null)
    }

    fun login() {
        if (uiState.username.isBlank() || uiState.password.isBlank()) {
            uiState = uiState.copy(errorMessage = "Please enter username and password")
            return
        }

        uiState = uiState.copy(isLoading = true, errorMessage = null)

        viewModelScope.launch {
            val result = loginUseCase(uiState.username, uiState.password)
            uiState = when {

                result.isSuccess -> {

                    uiState.copy(
                        isLoading = false,
                        isLoginSuccessful = true,
                        errorMessage = null
                    )

                }

                result.isFailure ->
                    uiState.copy(
                        isLoading = false,
                        errorMessage = result.exceptionOrNull()?.message ?: "Login failed",
                        isLoginSuccessful = false
                    )

                else -> uiState
            }
        }
    }

    fun resetLoginState() {
        uiState = uiState.copy(isLoginSuccessful = false, errorMessage = null)
    }

    fun onPasswordVisibilityChanged() {
        uiState = uiState.copy(passwordVisible = !uiState.passwordVisible)
    }
}




