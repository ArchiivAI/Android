package com.example.archivai.presentation.screens.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archivai.domain.usecases.home.GetUserDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserDetailsUseCase: GetUserDetails
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getUserPic()
        getUserName()
    }

    fun getUserName(){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val userDetails = getUserDetailsUseCase.invoke()
                Log.d("ViewModel", "user details fetched: $userDetails")
                _uiState.value = _uiState.value.copy(isLoading = false,
                    userName = userDetails.firstName +" "+userDetails.lastName
                )
            } catch (e: Exception) {
                Log.e("ViewModel", "Failed to fetch user details", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )
            }
        }
    }

    fun logOut(onLoggedOut: () -> Unit) {
        _uiState.value = _uiState.value.copy(isLoggingOut = true)


        viewModelScope.launch {
            delay(1500)

            _uiState.value = _uiState.value.copy(isLoggingOut = false)
            onLoggedOut()
        }
    }

    fun getUserPic(){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val userDetails = getUserDetailsUseCase.invoke()
                Log.d("ViewModel", "user image fetched: ${userDetails.imageUrl}")
                _uiState.value = _uiState.value.copy(isLoading = false,
                    imageUrl = userDetails.imageUrl
                )
            } catch (e: Exception) {
                Log.e("ViewModel", "Failed to fetch user details", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )
            }
        }
    }

    fun showLogOutDialog() {
        _uiState.value = _uiState.value.copy(showLogOutDialog = true)
    }

    fun hideLogOutDialog() {
        _uiState.value = _uiState.value.copy(showLogOutDialog = false)
    }


}


