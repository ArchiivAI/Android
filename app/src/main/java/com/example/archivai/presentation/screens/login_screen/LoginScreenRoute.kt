package com.example.archivai.presentation.screens.login_screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.archivai.presentation.navigation.Screens

fun NavGraphBuilder.loginScreenRoute(navController: NavController){

    composable <Screens.Login> {
        LoginScreen(navController)
    }

}