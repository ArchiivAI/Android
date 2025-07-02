package com.example.archivai.presentation.screens.employees.add_new_employee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archivai.domain.entities.Role
import com.example.archivai.domain.usecases.employees.CreateEmployeeUseCase
import com.example.archivai.domain.usecases.roles.GetRolesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewEmployeeViewModel @Inject constructor(
    val addEmployeeUseCase: CreateEmployeeUseCase,
    val getRolesUseCase: GetRolesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddNewEmployeeUiState())
    val uiState = _uiState

    private val _uiEvent = MutableSharedFlow<AddNewEmployeeUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        getRoles()
    }

    fun getRoles() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val roles = getRolesUseCase.invoke()
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    availableRoles = roles,
                    roles = roles
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )
            }
        }
    }

    fun addEmployee() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                addEmployeeUseCase.invoke(
                    _uiState.value.firstName,
                    _uiState.value.lastName,
                    _uiState.value.email,
                    _uiState.value.selectedRoles.map { it.id }
                )
                _uiEvent.emit(AddNewEmployeeUiEvent.ShowToast("Employee Added Successfully"))
                _uiEvent.emit(AddNewEmployeeUiEvent.NavigateBack)

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    firstName = "",
                    lastName = "",
                    email = "",
                    selectedRoles = emptyList()
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )
            }
        }
    }

    fun updateFirstName(name: String) {
        _uiState.value = _uiState.value.copy(firstName = name)
    }

    fun updateLastName(name: String) {
        _uiState.value = _uiState.value.copy(lastName = name)
    }

    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun toggleDropdown() {
        _uiState.value = _uiState.value.copy(
            isDropdownExpanded = !_uiState.value.isDropdownExpanded
        )
    }

    fun selectRole(role: Role) {
        val updatedSelected = _uiState.value.selectedRoles + role
        val updatedAvailable = _uiState.value.availableRoles - role
        _uiState.value = _uiState.value.copy(
            selectedRoles = updatedSelected,
            availableRoles = updatedAvailable,
            isDropdownExpanded = false
        )
    }

    fun removeRole(role: Role) {
        val updatedSelected = _uiState.value.selectedRoles - role
        val updatedAvailable = _uiState.value.availableRoles + role
        _uiState.value = _uiState.value.copy(
            selectedRoles = updatedSelected,
            availableRoles = updatedAvailable
        )
    }







}