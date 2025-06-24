package com.example.archivai.presentation.screens.roles.roles_screen

sealed class RolesUiEvent {
    data class ShowToast(val message: String) : RolesUiEvent()
}