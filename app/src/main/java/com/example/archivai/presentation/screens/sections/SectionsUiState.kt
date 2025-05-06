package com.example.archivai.presentation.screens.sections

data class SectionsUiState (
    val isLoading : Boolean = false,
    val sections : List<SectionResponseModel> =emptyList(),
    val showRenameDialog : Boolean = false,
    val showDeleteDialog : Boolean = false,
    val showEditPermissionsDialog : Boolean = false,
    val isGridView : Boolean = false,
    val showFabOptions : Boolean = false,
    val showErrorMessage : String? = null,
    val selectedIndex  : Int = 1,
    val showBottomSheet : Boolean = false



)