package com.example.archivai.presentation.screens.roles.roles_screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.archivai.presentation.navigation.Screens

fun NavGraphBuilder.rolesScreenRoute(navController: NavController){

    composable <Screens.Roles> {
        RolesScreen(navController)
    }

}