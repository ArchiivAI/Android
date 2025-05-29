package com.example.archivai.presentation.screens.login_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = "",
    val isLoginSuccessful: Boolean = false,
    var passwordVisible: Boolean = false,
    var rememberMe: Boolean = false
)
