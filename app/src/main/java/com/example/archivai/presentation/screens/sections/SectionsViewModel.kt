package com.example.archivai.presentation.screens.sections


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.archivai.domain.repository.sections.SectionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class SectionsViewModel @Inject constructor(
    val repository: SectionsRepository
) : ViewModel(){
    private val _uiState = MutableStateFlow(SectionsUiState())
    val uiState = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<SectionsUiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()













}


