package com.example.archivai.presentation.screens.folders

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archivai.domain.entities.Folder
import com.example.archivai.domain.usecases.folders.CreateFolderUseCase
import com.example.archivai.domain.usecases.folders.CreateSubFolderUseCase
import com.example.archivai.domain.usecases.folders.DeleteFolderUseCase
import com.example.archivai.domain.usecases.folders.GetFolderInFolderUseCase
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
    private val getFoldersInSectionUseCase: GetFoldersInSectionUseCase,
    private val getSubFoldersUseCase: GetFolderInFolderUseCase,
    private val createFolderUseCase: CreateFolderUseCase,
    private val deleteFolderUseCase: DeleteFolderUseCase,
    private val renameFolderUseCase: RenameFolderUseCase,
    private val createSubFolderUseCase: CreateSubFolderUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FolderUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<FoldersUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()


    fun getFolders(sectionId: Int, folderId: Int?) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val folders = if (folderId == null) {
                    Log.d("ViewModel", "Fetching section root folders for sectionId=$sectionId")
                    getFoldersInSectionUseCase(sectionId)
                } else {
                    Log.d("ViewModel", "Fetching subfolders for folderId=$folderId")
                    getSubFoldersUseCase(folderId)
                }

                _uiState.value = _uiState.value.copy(isLoading = false, folders = folders)
            } catch (e: Exception) {
                Log.e("ViewModel", "Failed to fetch folders", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )
            }
        }
    }

    fun createFolder(folderName: String, sectionId: Int, folderId: Int?) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val result = if (folderId == null) {
                createFolderUseCase(folderName, sectionId)
            } else { createSubFolderUseCase(folderName, folderId)
            }

            result.onSuccess {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isCreateFolderDialogVisible = false
                )
                _uiEvent.emit(FoldersUiEvent.ShowToast("Folder created successfully"))
                getFolders(sectionId, folderId)
            }.onFailure {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "An unexpected error occurred"
                )
                _uiEvent.emit(FoldersUiEvent.ShowToast("Failed to create folder"))
            }
        }
    }

    fun renameFolder(folderId: Int, name: String, sectionId: Int, parentFolderId: Int?) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            renameFolderUseCase(name, folderId)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isRenameFolderDialogVisible = false
                    )
                    getFolders(sectionId, parentFolderId)
                    _uiEvent.emit(FoldersUiEvent.ShowToast("Folder renamed successfully"))
                }
                .onFailure {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "An unexpected error occurred"
                    )
                    _uiEvent.emit(FoldersUiEvent.ShowToast("Failed to rename folder"))
                }
        }
    }

    fun deleteFolder(folderId: Int, sectionId: Int, parentFolderId: Int?) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                deleteFolderUseCase(folderId)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    selectedFolder = null,
                    isDeleteFolderDialogVisible = false
                )
                getFolders(sectionId, parentFolderId)
                _uiEvent.emit(FoldersUiEvent.ShowToast("Folder deleted successfully"))
            } catch (e: Exception) {
                Log.e("ViewModel", "Failed to delete folder", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )
                _uiEvent.emit(FoldersUiEvent.ShowToast("Failed to delete folder"))
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
        _uiState.value = _uiState.value.copy(
            isFabBottomSheetVisible = false,
            isCreateFolderDialogVisible = true
        )
    }

    fun hideCreateFolderDialog() {
        _uiState.value = _uiState.value.copy(isCreateFolderDialogVisible = false)
    }

    fun showDeleteFolderDialog() {
        _uiState.value = _uiState.value.copy(
            isSettingsBottomSheetVisible = false,
            isDeleteFolderDialogVisible = true
        )
    }

    fun hideDeleteFolderDialog() {
        _uiState.value = _uiState.value.copy(isDeleteFolderDialogVisible = false)
    }

    fun showRenameFolderDialog() {
        _uiState.value = _uiState.value.copy(
            isSettingsBottomSheetVisible = false,
            isRenameFolderDialogVisible = true
        )
    }

    fun hideRenameFolderDialog() {
        _uiState.value = _uiState.value.copy(isRenameFolderDialogVisible = false)
    }
}
