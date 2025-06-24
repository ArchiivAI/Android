package com.example.archivai.presentation.screens.roles.add_new_role

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.archivai.presentation.navigation.Screens

fun NavGraphBuilder.addNewRolesScreenRoute(navController: NavController){

    composable <Screens.AddNewRole> {
        AddNewRoleScreen(navController)
    }

}