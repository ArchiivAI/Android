package com.example.archivai.presentation.screens.roles.roles_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archivai.domain.entities.Role
import com.example.archivai.domain.entities.Section
import com.example.archivai.domain.usecases.roles.GetRolesUseCase
import com.example.archivai.domain.usecases.roles.RenameRoleUseCase
import com.example.archivai.presentation.screens.sections.SectionsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RolesViewModel @Inject constructor (
    val getRolesUseCase: GetRolesUseCase,
    val renameRoleUseCase : RenameRoleUseCase
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

    fun renameRole(roleId: Int, name: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            renameRoleUseCase.invoke(roleId, name)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        showRenameRoleDialog = false
                    )
                }
                .onFailure {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "An unexpected error occurred"
                    )
                }


        }

    }
    fun selectedRole(role: Role) {
        _uiState.value = _uiState.value.copy(selectedRole = role)
    }
    fun showSettingsBottomSheet() {
        _uiState.value = _uiState.value.copy(showSettingsBottomSheet = true)
    }

    fun hideSettingsBottomSheet() {
        _uiState.value = _uiState.value.copy(showSettingsBottomSheet = false)
    }

    fun showRoleRenameDialog() {
        _uiState.value = _uiState.value.copy(showRenameRoleDialog = true)
    }

    fun hideRoleRenameDialog() {
        _uiState.value = _uiState.value.copy(showRenameRoleDialog = false)
    }




}