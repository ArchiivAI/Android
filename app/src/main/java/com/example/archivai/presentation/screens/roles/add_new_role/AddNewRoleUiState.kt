package com.example.archivai.presentation.screens.roles.add_new_role

import com.example.archivai.domain.entities.Employee

data class AddNewRoleUiState(
    val name: String = "",
    val employees: List<Employee> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val availableEmployees: List<Employee> = emptyList(),
    val selectedEmployees: List<Employee> = emptyList(),
    val isDropdownExpanded: Boolean = false,
)