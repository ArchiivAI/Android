package com.example.archivai.presentation.screens.roles.roles_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archivai.domain.usecases.roles.GetRolesUseCase
import com.example.archivai.presentation.screens.sections.SectionsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RolesViewModel @Inject constructor (
    val getRolesUseCase: GetRolesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(RolesUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getRoles()
    }

    fun getRoles(){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val roles = getRolesUseCase.invoke()
                Log.d("ViewModel", "roles fetched: ${roles.size}")
                _uiState.value = _uiState.value.copy(isLoading = false, roles = roles)
            } catch (e: Exception) {
                Log.e("ViewModel", "Failed to fetch roles", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )
            }
        }
    }



}