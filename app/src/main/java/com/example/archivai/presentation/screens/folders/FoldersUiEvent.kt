package com.example.archivai.presentation.screens.folders

sealed class FoldersUiEvent {
    data class ShowToast(val message: String) : FoldersUiEvent()
}