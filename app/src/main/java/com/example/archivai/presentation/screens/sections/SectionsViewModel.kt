package com.example.archivai.presentation.screens.sections


import com.example.archivai.domain.repository.sections.SectionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@HiltViewModel
class SectionsViewModel @Inject constructor(
    val repository: SectionsRepository
) : ViewModel(){
    private val _uiState = MutableStateFlow(SectionsUiState())
    val uiState : StateFlow<SectionsUiState> = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<SectionsUiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()




    //load sections
     fun loadSections(token : String, page : Int){
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val sections = repository.getSections(token,page)
                _uiState.update { it.copy(isLoading = false, sections=sections) }

            }catch (e: Exception){
                _uiState.update { it.copy(isLoading = false, showErrorMessage = e.message) }
                _eventFlow.emit(SectionsUiEvents.ShowToast("Failed to load Sections"))
            }

        }
    }
    // rename dialog
    fun renameDialogToggle(show : Boolean){
        _uiState.update { it.copy(showRenameDialog = show) }
    }

    //delete dialog
    fun deleteDialog(show : Boolean){
        _uiState.update { it.copy(showDeleteDialog = show) }
    }

    //









}


