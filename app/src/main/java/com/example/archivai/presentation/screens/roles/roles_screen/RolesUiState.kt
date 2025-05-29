package com.example.archivai.presentation.screens.roles.roles_screen

import com.example.archivai.domain.entities.Role
data class RolesUiState (
    val isLoading : Boolean = false,
    val roles : List<Role> =emptyList(),
    val error: String? = null
)