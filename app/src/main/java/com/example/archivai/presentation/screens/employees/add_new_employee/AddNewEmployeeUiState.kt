package com.example.archivai.presentation.screens.employees.add_new_employee

import com.example.archivai.domain.entities.Role

data class AddNewEmployeeUiState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val roles: List<Role> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val availableRoles: List<Role> = emptyList(),
    val selectedRoles: List<Role> = emptyList(),
    val isDropdownExpanded: Boolean = false,
)
