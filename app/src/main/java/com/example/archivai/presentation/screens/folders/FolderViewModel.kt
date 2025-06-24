package com.example.archivai.presentation.screens.folders

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archivai.domain.usecases.folders.GetFoldersInSectionUseCase
import com.example.archivai.presentation.screens.folder.FolderUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FolderViewModel @Inject constructor(
    val getFoldersInSectionUseCase: GetFoldersInSectionUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(FolderUiState())
    val uiState = _uiState.asStateFlow()


    fun getFolders(sectionId : Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                Log.d("ViewModel", "Fetching folders for sectionId: $sectionId")
                val folders = getFoldersInSectionUseCase(sectionId)
                Log.d("ViewModel", "folders fetched: ${folders}")
                _uiState.value = _uiState.value.copy(isLoading = false, folders = folders)
            } catch (e: Exception) {
                Log.e("ViewModel", "Failed to folders sections", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )

            }


        }


    }


}