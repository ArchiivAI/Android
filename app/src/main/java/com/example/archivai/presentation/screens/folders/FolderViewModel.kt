package com.example.archivai.presentation.screens.folders

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archivai.domain.entities.Folder
import com.example.archivai.domain.usecases.folders.CreateFolderUseCase
import com.example.archivai.domain.usecases.folders.CreateSubFolderUseCase
import com.example.archivai.domain.usecases.folders.DeleteFolderUseCase
import com.example.archivai.domain.usecases.folders.GetFilesUseCase
import com.example.archivai.domain.usecases.folders.GetFolderInFolderUseCase
import com.example.archivai.domain.usecases.folders.GetFoldersInSectionUseCase
import com.example.archivai.domain.usecases.folders.RenameFolderUseCase
import com.example.archivai.domain.usecases.folders.UploadFileUseCase
import com.example.archivai.presentation.screens.folder.FolderUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class FolderViewModel @Inject constructor(
    private val getFoldersInSectionUseCase: GetFoldersInSectionUseCase,
    private val getSubFoldersUseCase: GetFolderInFolderUseCase,
    private val createFolderUseCase: CreateFolderUseCase,
    private val deleteFolderUseCase: DeleteFolderUseCase,
    private val renameFolderUseCase: RenameFolderUseCase,
    private val createSubFolderUseCase: CreateSubFolderUseCase,
    private val getFilesUseCase: GetFilesUseCase,
    private val uploadFileUseCase: UploadFileUseCase
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

    fun uploadFiles(folderId: Int?) {
        Log.d("FolderViewModel", "uploadFiles called with folderId: $folderId")

        if (folderId == null) {
            Log.e("FolderViewModel", "folderId is null, cannot upload files")
            viewModelScope.launch {
                _uiEvent.emit(FoldersUiEvent.ShowToast("Error: No folder selected"))
            }
            return
        }

        viewModelScope.launch {
            Log.d("FolderViewModel", "Starting upload process...")
            _uiState.value = _uiState.value.copy(isUploading = true)

            try {
                val filesToUpload = if (_uiState.value.capturedImage != null) {
                    Log.d("FolderViewModel", "Uploading captured image: ${_uiState.value.capturedImage?.name}")
                    listOf(_uiState.value.capturedImage!!)
                } else {
                    Log.d("FolderViewModel", "Uploading selected files: ${_uiState.value.selectedFiles.size} files")
                    _uiState.value.selectedFiles
                }

                if (filesToUpload.isEmpty()) {
                    Log.w("FolderViewModel", "No files to upload")
                    _uiEvent.emit(FoldersUiEvent.ShowToast("No files selected"))
                    return@launch
                }

                var uploadedCount = 0
                var failedCount = 0

                filesToUpload.forEach { file ->
                    Log.d("FolderViewModel", "Uploading file: ${file.name}, size: ${file.length()} bytes")

                    val result = uploadFileUseCase(folderId, file)
                    result.onSuccess {
                        uploadedCount++
                        Log.d("FolderViewModel", "Successfully uploaded: ${file.name}")
                    }.onFailure { exception ->
                        failedCount++
                        Log.e("FolderViewModel", "Failed to upload: ${file.name}", exception)
                    }
                }

                // Show result toast
                val message = when {
                    failedCount == 0 -> "Successfully uploaded $uploadedCount file(s)"
                    uploadedCount == 0 -> "Failed to upload all files"
                    else -> "Uploaded $uploadedCount file(s), $failedCount failed"
                }

                Log.d("FolderViewModel", "Upload completed: $message")
                _uiEvent.emit(FoldersUiEvent.ShowToast(message))

                // Refresh files list if any uploads succeeded
                if (uploadedCount > 0) {
                    Log.d("FolderViewModel", "Refreshing files list...")
                    getFiles(folderId)
                }

            } catch (e: Exception) {
                Log.e("FolderViewModel", "Upload error", e)
                _uiEvent.emit(FoldersUiEvent.ShowToast("Upload error: ${e.message}"))
            } finally {
                Log.d("FolderViewModel", "Cleaning up upload state...")
                _uiState.value = _uiState.value.copy(
                    isUploading = false,
                    selectedFiles = emptyList(),
                    capturedImage = null
                )
            }
        }
    }

    fun getFiles(folderId: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val files = getFilesUseCase(folderId)
                Log.d("ViewModel", "Fetched files: $files for folderId=$folderId")
                _uiState.value = _uiState.value.copy(isLoading = false, files = files)
            } catch (e: Exception) {
                Log.e("ViewModel", "Failed to fetch files", e)
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
            } else {
                createSubFolderUseCase(folderName, folderId)
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

    fun addSelectedFiles(files: List<File>) {
        _uiState.value = _uiState.value.copy(
            selectedFiles = files
        )
    }

    fun setCapturedImage(file: File) {
        _uiState.value = _uiState.value.copy(
            capturedImage = file
        )
    }

    fun clearSelectedFiles() {
        _uiState.value = _uiState.value.copy(
            selectedFiles = emptyList()
        )
    }

    fun removeSelectedFile(file: File) {
        _uiState.value = _uiState.value.copy(
            selectedFiles = _uiState.value.selectedFiles.filter { it != file }
        )
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

    fun showFileOptionsBottomSheet() {
        _uiState.value = _uiState.value.copy(isFileOptionsBottomSheetVisible = true)
    }

    fun hideFileOptionsBottomSheet() {
        _uiState.value = _uiState.value.copy(isFileOptionsBottomSheetVisible = false)
    }

    fun showUploadConfirmationBottomSheet() {
        _uiState.value = _uiState.value.copy(isUploadConfirmationBottomSheetVisible = true)
    }

    fun hideUploadConfirmationBottomSheet() {
        _uiState.value = _uiState.value.copy(isUploadConfirmationBottomSheetVisible = false)
    }

    fun enablePhotoMode() {
        _uiState.value = _uiState.value.copy(isPhotoMode = true)
    }

    fun disablePhotoMode() {
        _uiState.value = _uiState.value.copy(isPhotoMode = false)
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
