package com.example.archivai.presentation.screens.roles.role_profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.archivai.presentation.navigation.Screens

fun NavGraphBuilder.roleProfileScreenRoute(
    navController: NavController
) {
    composable <Screens.RoleProfile> {
        RoleProfileScreen(navController)
    }
}