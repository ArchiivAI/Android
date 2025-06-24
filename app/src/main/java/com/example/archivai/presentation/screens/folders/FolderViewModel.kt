package com.example.archivai.presentation.screens.folders

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archivai.domain.usecases.folders.CreateFolderUseCase
import com.example.archivai.domain.usecases.folders.GetFoldersInSectionUseCase
import com.example.archivai.presentation.screens.folder.FolderUiState
import com.example.archivai.presentation.screens.sections.SectionsUiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FolderViewModel @Inject constructor(
    val getFoldersInSectionUseCase: GetFoldersInSectionUseCase,
    val createFolderUseCase: CreateFolderUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(FolderUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<FoldersUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()


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

    fun createFolder(folderName: String, sectionId: Int){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            createFolderUseCase.invoke(folderName,sectionId)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isCreateFolderDialogVisible = false
                    )
                    Log.d("vm", folderName)
                    _uiEvent.emit(FoldersUiEvent.ShowToast("Folder created successfully"))
                    getFolders(sectionId)
                }
                .onFailure {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "An unexpected error occurred"
                    )
                    _uiEvent.emit(FoldersUiEvent.ShowToast("Failed to Create Folder"))
                }


        }

    }



    fun showFabBottomSheet() {
        _uiState.value = _uiState.value.copy(isFabBottomSheetVisible = true)
    }
    fun hideFabBottomSheet() {
        _uiState.value = _uiState.value.copy(isFabBottomSheetVisible = false)
    }
    fun showCreateFolderDialog() {
        _uiState.value = _uiState.value.copy(isFabBottomSheetVisible = false)
        _uiState.value = _uiState.value.copy(isCreateFolderDialogVisible = true)
    }
    fun hideCreateFolderDialog() {
        _uiState.value = _uiState.value.copy(isCreateFolderDialogVisible = false)
    }



}