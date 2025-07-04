package com.example.archivai.presentation.screens.roles.add_new_role

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archivai.domain.entities.Employee
import com.example.archivai.domain.usecases.employees.GetEmployeesUseCase
import com.example.archivai.domain.usecases.roles.CreateRoleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewRoleViewModel @Inject constructor(
    val addRoleUseCase: CreateRoleUseCase,
    val getEmployeesUseCase: GetEmployeesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddNewRoleUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<AddNewRoleUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        getEmployees()
    }

    fun getEmployees() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val employees = getEmployeesUseCase.invoke()
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    availableEmployees = employees,
                    employees = employees
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )
            }
        }
    }

    fun addRole() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                addRoleUseCase.invoke(
                    _uiState.value.name,
                    _uiState.value.selectedEmployees.map { it.id }
                )
                _uiEvent.emit(AddNewRoleUiEvent.ShowToast("Role Added Successfully"))
                _uiEvent.emit(AddNewRoleUiEvent.NavigateBack)

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    name = "",
                    selectedEmployees = emptyList()
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )
            }
        }
    }

    fun updateName(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }

    fun toggleDropdown() {
        _uiState.value = _uiState.value.copy(
            isDropdownExpanded = !_uiState.value.isDropdownExpanded
        )
    }

    fun selectEmployee(employee: Employee) {
        val updatedSelected = _uiState.value.selectedEmployees + employee
        val updatedAvailable = _uiState.value.availableEmployees - employee
        _uiState.value = _uiState.value.copy(
            selectedEmployees = updatedSelected,
            availableEmployees = updatedAvailable,
            isDropdownExpanded = false
        )
    }

    fun removeEmployee(employee: Employee) {
        val updatedSelected = _uiState.value.selectedEmployees - employee
        val updatedAvailable = _uiState.value.availableEmployees + employee
        _uiState.value = _uiState.value.copy(
            selectedEmployees = updatedSelected,
            availableEmployees = updatedAvailable
        )
    }
}