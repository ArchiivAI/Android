package com.example.archivai.presentation.screens.sections

sealed class SectionsUiEvents {
    data class ShowToast(val message: String) : SectionsUiEvents()
    object NavigateToScan : SectionsUiEvents()
    object NavigateToAddFilesWithAI : SectionsUiEvents()
    object NavigateToCreateSection : SectionsUiEvents()




}