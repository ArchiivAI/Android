package com.example.archivai.presentation.screens.roles.roles_screen

import com.example.archivai.domain.entities.Role
data class RolesUiState (
    val isLoading : Boolean = false,
    val roles : List<Role> =emptyList(),
    val error: String? = null,
    val showRenameRoleDialog : Boolean = false,
    val showDeleteRoleDialog : Boolean = false,
    val showSettingsBottomSheet : Boolean = false,
    val selectedRole : Role? = null
)