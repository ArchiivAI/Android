package com.example.archivai.presentation.screens.employees.employeesScreen

import com.example.archivai.domain.entities.Employee

data class EmployeesUiState(
    val isLoading : Boolean = false,
    val employees : List<Employee> =emptyList(),
    val error: String? = null,
    val isSettingsBottomSheetVisible : Boolean = false,
    val isDeleteBottomSheetVisible : Boolean = false,
    val selectedEmployee : Employee? = null,
)
