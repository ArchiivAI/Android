package com.example.archivai.sections.presentation

import android.os.Message

sealed class SectionsUiEvents {
    data class ShowToast(val message: String) : SectionsUiEvents()
    object NavigateToScan : SectionsUiEvents()
    object NavigateToAddFilesWithAI : SectionsUiEvents()
    object NavigateToCreateSection : SectionsUiEvents()




}