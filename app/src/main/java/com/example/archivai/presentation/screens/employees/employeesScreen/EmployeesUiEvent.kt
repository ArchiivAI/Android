package com.example.archivai.presentation.screens.employees.employeesScreen

sealed class EmployeesUiEvent {
data class ShowToast(val message: String) : EmployeesUiEvent()

}