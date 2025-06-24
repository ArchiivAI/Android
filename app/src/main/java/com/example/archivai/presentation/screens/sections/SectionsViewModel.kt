package com.example.archivai.presentation.screens.sections


import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archivai.domain.entities.Section
import com.example.archivai.domain.repository.sections.SectionsRepository
import com.example.archivai.domain.usecases.sections.CreateSectionUseCase
import com.example.archivai.domain.usecases.sections.DeleteSectionUseCase
import com.example.archivai.domain.usecases.sections.GetSectionsUseCase
import com.example.archivai.domain.usecases.sections.RenameSectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SectionsViewModel @Inject constructor(
    val getSectionsUseCase: GetSectionsUseCase,
    val createSectionUseCase: CreateSectionUseCase,
    val deleteSectionUseCase: DeleteSectionUseCase,
    val renameSectionUseCase: RenameSectionUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SectionsUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<SectionsUiEvents>()
    val uiEvent = _uiEvent.asSharedFlow()


    init {
        getSections()
    }

    private fun getSections() {

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val sections = getSectionsUseCase()
                Log.d("ViewModel", "Sections fetched: ${sections.size}")
                _uiState.value = _uiState.value.copy(isLoading = false, sections = sections)
            } catch (e: Exception) {
                Log.e("ViewModel", "Failed to fetch sections", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )
                _uiEvent.emit(SectionsUiEvents.ShowToast("Failed to Get Section"))
            }


        }


    }

    fun createSection(name: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            createSectionUseCase.invoke(name)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        showCreateDialog = false
                    )
                    Log.d("vm", name)
                    _uiEvent.emit(SectionsUiEvents.ShowToast("Section created successfully"))
                    getSections()
                }
                .onFailure {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "An unexpected error occurred"
                    )
                    _uiEvent.emit(SectionsUiEvents.ShowToast("Failed to Create Section"))
                }


        }

    }

    fun renameSection(sectionId: Int, name: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            renameSectionUseCase.invoke(sectionId, name)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        showRenameDialog = false
                    )
                    getSections()
                    _uiEvent.emit(SectionsUiEvents.ShowToast("Section renamed successfully"))
                }
                .onFailure {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "An unexpected error occurred"
                    )
                    _uiEvent.emit(SectionsUiEvents.ShowToast("Failed to Rename Section"))
                }


        }

    }

     fun deleteSection(sectionId: Int) {

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                deleteSectionUseCase(sectionId)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    selectedSection = null,
                    showDeleteDialog = false
                )
                getSections()
                _uiEvent.emit(SectionsUiEvents.ShowToast("Section deleted successfully"))
            } catch (e: Exception) {
                Log.e("ViewModel", "Failed to delete section", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )
                _uiEvent.emit(SectionsUiEvents.ShowToast("Failed to Delete Section"))
            }
        }


    }


    fun selectSection(section: Section) {
        _uiState.value = _uiState.value.copy(selectedSection = section)
    }

    fun showSettingsBottomSheet() {
        _uiState.value = _uiState.value.copy(showSettingsBottomSheet = true)
    }

    fun hideSettingsBottomSheet() {
        _uiState.value = _uiState.value.copy(showSettingsBottomSheet = false)
    }

    fun showFabBottomSheet() {
        _uiState.value = _uiState.value.copy(showFabBottomSheet = true)
    }

    fun hideFabBottomSheet() {
        _uiState.value = _uiState.value.copy(showFabBottomSheet = false)
    }

    fun showRenameDialog() {
        _uiState.value = _uiState.value.copy(showRenameDialog = true)
    }

    fun hideRenameDialog() {
        _uiState.value = _uiState.value.copy(showRenameDialog = false)
    }

    fun showCreateDialog() {
        _uiState.value = _uiState.value.copy(showFabBottomSheet = false)
        _uiState.value = _uiState.value.copy(showCreateDialog = true)
    }

    fun hideCreateDialog() {
        _uiState.value = _uiState.value.copy(showCreateDialog = false)
    }

    fun showDeleteDialog() {
        _uiState.value = _uiState.value.copy(showFabBottomSheet = false)
        _uiState.value = _uiState.value.copy(showDeleteDialog = true)
    }

    fun hideDeleteDialog() {
        _uiState.value = _uiState.value.copy(showDeleteDialog = false)
    }


}


