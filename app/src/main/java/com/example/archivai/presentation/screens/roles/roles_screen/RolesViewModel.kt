package com.example.archivai.presentation.screens.roles.roles_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archivai.domain.entities.Role
import com.example.archivai.domain.entities.Section
import com.example.archivai.domain.usecases.roles.DeleteRoleUseCase
import com.example.archivai.domain.usecases.roles.GetRolesUseCase
import com.example.archivai.domain.usecases.roles.RenameRoleUseCase
import com.example.archivai.presentation.screens.sections.SectionsUiEvents
import com.example.archivai.presentation.screens.sections.SectionsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RolesViewModel @Inject constructor(
    val getRolesUseCase: GetRolesUseCase,
    val renameRoleUseCase: RenameRoleUseCase,
    val deleteRoleUseCase: DeleteRoleUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(RolesUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<RolesUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        getRoles()
    }

    fun getRoles() {
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
                _uiEvent.emit(RolesUiEvent.ShowToast("Failed to Fetch Roles"))

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
                    _uiEvent.emit(RolesUiEvent.ShowToast("Role Renamed successfully"))

                }
                .onFailure {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "An unexpected error occurred"
                    )
                    _uiEvent.emit(RolesUiEvent.ShowToast("Failed to Rename Role"))
                }


        }

    }

    fun deleteRole(roleId: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                deleteRoleUseCase.invoke(roleId)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    selectedRole = null,
                    showDeleteRoleDialog = false
                )
                _uiEvent.emit(RolesUiEvent.ShowToast("Role Deleted successfully"))

                getRoles()
            } catch (e: Exception) {
                Log.e("ViewModel", "Failed to delete role", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )
                _uiEvent.emit(RolesUiEvent.ShowToast("Failed to Delete Role"))

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

    fun showDeleteRoleDialog() {
        _uiState.value = _uiState.value.copy(showSettingsBottomSheet = false)
        _uiState.value = _uiState.value.copy(showDeleteRoleDialog = true)
    }

    fun hideDeleteRoleDialog() {
        _uiState.value = _uiState.value.copy(showDeleteRoleDialog = false)
    }


}