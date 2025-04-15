package com.example.archivai.sections.presentation

import com.example.archivai.sections.data.models.Section

data class SectionsUiState (
    val isLoading : Boolean = false,
    val sections : List<Section> =emptyList(),
    val showRenameDialog : Boolean = false,
    val showDeleteDialog : Boolean = false,
    val showEditPermissionsDialog : Boolean = false,
    val isGridView : Boolean = false,
    val showFabOptions : Boolean = false,
    val showErrorMessage : String? = null



)