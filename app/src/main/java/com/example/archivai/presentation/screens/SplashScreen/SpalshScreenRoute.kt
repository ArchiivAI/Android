package com.example.archivai.presentation.screens.SplashScreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.archivai.presentation.navigation.Screens

fun NavGraphBuilder.splashScreenRoute(navController: NavController){

    composable <Screens.Splash> {
        SplashScreen(navController)
    }

}