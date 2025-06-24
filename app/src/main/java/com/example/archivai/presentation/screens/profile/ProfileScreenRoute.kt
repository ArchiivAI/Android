package com.example.archivai.presentation.screens.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.archivai.presentation.navigation.Screens

fun NavGraphBuilder.profileScreenRoute(navController: NavController){

    composable <Screens.Profile> {
        ProfileScreen(navController)
    }

}