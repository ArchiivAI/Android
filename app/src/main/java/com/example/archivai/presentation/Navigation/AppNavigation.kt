package com.example.archivai.presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

sealed class Screen(route : String){
    object loginScreen : Screen("login_screen")
    object onBoardingScreen1 : Screen("onBoarding_screen1")
    object onBoardingScreen2 : Screen("onBoarding_screen2")
    object onBoardingScreen3 : Screen("onBoarding_screen3")

    
}

@Composable
fun AppNavigation(navController : NavHostController) {
    NavHost(navController =navController , startDestination =  "") { }



    
}