package com.example.archivai.presentation.screens.folders

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archivai.domain.entities.Folder
import com.example.archivai.domain.usecases.folders.CreateFolderUseCase
import com.example.archivai.domain.usecases.folders.DeleteFolderUseCase
import com.example.archivai.domain.usecases.folders.GetFoldersInSectionUseCase
import com.example.archivai.domain.usecases.folders.RenameFolderUseCase
import com.example.archivai.presentation.screens.folder.FolderUiState
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
    val createFolderUseCase: CreateFolderUseCase,
    val deleteFolderUseCase: DeleteFolderUseCase,
    val renameFolderUseCase : RenameFolderUseCase
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

    fun renameFolder(folderId: Int, name: String,sectionId: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            renameFolderUseCase.invoke(name,folderId)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isRenameFolderDialogVisible = false
                    )
                    getFolders(sectionId)
                    _uiEvent.emit(FoldersUiEvent.ShowToast("Folder renamed successfully"))
                }
                .onFailure {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "An unexpected error occurred"
                    )
                    _uiEvent.emit(FoldersUiEvent.ShowToast("Failed to Rename Folder"))
                }


        }

    }


    fun deleteFolder(folderId: Int,sectionId: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                deleteFolderUseCase.invoke(folderId)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    selectedFolder = null,
                    isDeleteFolderDialogVisible = false
                )
                getFolders(sectionId)
                _uiEvent.emit(FoldersUiEvent.ShowToast("Folder deleted successfully"))
            } catch (e: Exception) {
                Log.e("ViewModel", "Failed to delete folder", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )
                _uiEvent.emit(FoldersUiEvent.ShowToast("Failed to Delete Folder"))
            }
        }


    }


    fun selectFolder(folder: Folder) {
        _uiState.value = _uiState.value.copy(selectedFolder = folder)
    }
    fun showSettingsBottomSheet() {
        _uiState.value = _uiState.value.copy(isSettingsBottomSheetVisible = true)
    }
    fun hideSettingsBottomSheet() {
        _uiState.value = _uiState.value.copy(isSettingsBottomSheetVisible = false)
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
    fun showDeleteFolderDialog() {
        _uiState.value = _uiState.value.copy(isSettingsBottomSheetVisible = false)
        _uiState.value = _uiState.value.copy(isDeleteFolderDialogVisible = true)
    }

    fun hideDeleteFolderDialog() {
        _uiState.value = _uiState.value.copy(isDeleteFolderDialogVisible = false)
    }

fun showRenameFolderDialog() {
        _uiState.value = _uiState.value.copy(isSettingsBottomSheetVisible = false)
        _uiState.value = _uiState.value.copy(isRenameFolderDialogVisible = true)
    }

    fun hideRenameFolderDialog() {
        _uiState.value = _uiState.value.copy(isRenameFolderDialogVisible = false)
    }



}