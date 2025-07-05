package com.example.archivai.presentation.screens.sections

import com.example.archivai.domain.entities.Employee
import com.example.archivai.domain.entities.Role
import com.example.archivai.domain.entities.Section

data class SectionsUiState (
    val selectedSection: Section? = null,
    val isLoading : Boolean = false,
    val sections : List<Section> =emptyList(),
    val employees: List<Employee> = emptyList(),
    val roles: List<Role> = emptyList(),
    val showRenameDialog : Boolean = false,
    val showDeleteDialog : Boolean = false,
    val showCreateDialog : Boolean = false,
    val showEditPermissionsDialog : Boolean = false,
    val isGridView : Boolean = false,
    val showFabBottomSheet : Boolean = false,
    val showErrorMessage : String? = null,
    val selectedIndex  : Int = 1,
    val showSettingsBottomSheet : Boolean = false,
    val error: String? = null
)