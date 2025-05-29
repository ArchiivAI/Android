package com.example.archivai.presentation.screens.contact_us

import androidx.lifecycle.ViewModel
import com.example.archivai.domain.usecases.authentication.ContactUsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ContactUsViewModel @Inject constructor(
    private val contactUsUseCase: ContactUsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState : StateFlow<UiState> = _uiState.asStateFlow()


    fun sendContactEmail(email: String) {
        if (!email.matches(Regex("^[A-Za-z0-9+_.-]+@(.+)$"))) {
            _uiState.value = UiState.Error("Invalid email format")
            return
        }
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val result = contactUsUseCase.invoke(email)
                _uiState.value = UiState.Success("Email Sent Successfully")
            }catch (e: Exception){
                _uiState.value = UiState.Error("Sending Email Failed")
            }


        }


    }

}