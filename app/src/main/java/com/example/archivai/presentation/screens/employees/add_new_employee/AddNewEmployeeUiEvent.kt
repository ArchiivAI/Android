package com.example.archivai.presentation.screens.employees.add_new_employee

sealed class AddNewEmployeeUiEvent {
    data class ShowToast(val message: String) : AddNewEmployeeUiEvent()
    object NavigateBack : AddNewEmployeeUiEvent()
}