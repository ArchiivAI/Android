package com.example.archivai.presentation.screens.roles.add_new_role

sealed class AddNewRoleUiEvent {
    data class ShowToast(val message: String) : AddNewRoleUiEvent()
    object NavigateBack : AddNewRoleUiEvent()
}