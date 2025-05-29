package com.example.archivai.presentation.screens.employees.employeesScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archivai.domain.usecases.employees.GetEmployeesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeesViewModel @Inject constructor(
    val getEmployeesUseCase: GetEmployeesUseCase
) : ViewModel(){
    private val _uiState = MutableStateFlow(EmployeesUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getEmployees()
    }

    fun getEmployees(){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val employees = getEmployeesUseCase.invoke()
                Log.d("ViewModel", "employees fetched: ${employees.size}")
                _uiState.value = _uiState.value.copy(isLoading = false, employees = employees)
            } catch (e: Exception) {
                Log.e("ViewModel", "Failed to fetch employees", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )
            }
        }
    }




}