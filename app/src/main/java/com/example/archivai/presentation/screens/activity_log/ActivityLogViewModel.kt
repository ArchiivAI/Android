package com.example.archivai.presentation.screens.activity_log

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archivai.domain.usecases.activity_logs.GetActivityLogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityLogViewModel @Inject constructor(
    val getActivityLogsUseCase: GetActivityLogsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ActivityLogUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getLogs()
    }

    fun getLogs() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val logs = getActivityLogsUseCase()
                Log.d("ActivityLogViewModel", "Fetched logs: $logs")
                _uiState.value = _uiState.value.copy(logs = logs, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message, isLoading = false)
                Log.e("ActivityLogViewModel", "Error fetching logs: ${e.message}", e)
            }
        }

    }

}