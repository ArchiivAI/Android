package com.example.archivai.presentation.screens.folder

import com.example.archivai.domain.entities.File
import com.example.archivai.domain.entities.Folder


data class FolderUiState (
    val error: String? = null,
    val isLoading : Boolean = false,
    val folders : List<Folder> =emptyList(),
    val isFabBottomSheetVisible : Boolean = false,
    val isCreateFolderDialogVisible : Boolean = false,
    val isDeleteFolderDialogVisible : Boolean = false,
    val isSettingsBottomSheetVisible : Boolean = false,
    val isRenameFolderDialogVisible : Boolean = false,
    val selectedFolder : Folder? = null,
    val files : List<File> = emptyList()
)