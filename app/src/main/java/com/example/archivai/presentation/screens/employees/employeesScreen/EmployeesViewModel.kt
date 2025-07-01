package com.example.archivai.presentation.screens.employees.employeesScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archivai.domain.entities.Employee
import com.example.archivai.domain.usecases.employees.DeleteEmployeeUseCase
import com.example.archivai.domain.usecases.employees.GetEmployeesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeesViewModel @Inject constructor(
    val getEmployeesUseCase: GetEmployeesUseCase,
    val deleteEmployeeUseCase: DeleteEmployeeUseCase
) : ViewModel(){
    private val _uiState = MutableStateFlow(EmployeesUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<EmployeesUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

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

    fun deleteEmployee(employeeId : Int){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                deleteEmployeeUseCase.invoke(employeeId)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    selectedEmployee = null,
                     isDeleteBottomSheetVisible = false
                )
                _uiEvent.emit(EmployeesUiEvent.ShowToast("Employee Deleted successfully"))

                getEmployees()
            } catch (e: Exception) {
                Log.e("ViewModel", "Failed to delete employee", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )
                _uiEvent.emit(EmployeesUiEvent.ShowToast("Failed to Delete Employee"))

            }
        }
    }
    fun selectEmployee(employee: Employee) {
        _uiState.value = _uiState.value.copy(selectedEmployee = employee)
    }

    fun showSettingsBottomSheet() {
        _uiState.value = _uiState.value.copy(isSettingsBottomSheetVisible = true)
    }
    fun hideSettingsBottomSheet() {
        _uiState.value = _uiState.value.copy(isSettingsBottomSheetVisible = false)
    }
    fun showDeleteDialog() {
        _uiState.value = _uiState.value.copy(isDeleteBottomSheetVisible = true)
    }
    fun hideDeleteDialog() {
        _uiState.value = _uiState.value.copy(isDeleteBottomSheetVisible = false)
    }






}