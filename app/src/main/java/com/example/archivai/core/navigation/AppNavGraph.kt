package com.example.archivai.core.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.archivai.auth.presentation.Intro.OnBoardingScreens.OnBoardingScreen1
import com.example.archivai.auth.presentation.Intro.OnBoardingScreens.OnBoardingScreen2
import com.example.archivai.auth.presentation.Intro.OnBoardingScreens.OnBoardingScreen3
import com.example.archivai.auth.presentation.Intro.SplashScreen.SplashScreen
import com.example.archivai.sections.presentation.SectionsScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph(navController: NavHostController) {
    AnimatedNavHost(
        navController =navController,
        startDestination = NavRoutes.OnBoarding1.route,
        enterTransition = { slideInHorizontally() },
        exitTransition = { slideOutHorizontally() },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }
    ){
        composable (route = NavRoutes.Splash.route){
            SplashScreen(navController)
        }
        composable (route = NavRoutes.OnBoarding1.route){
            OnBoardingScreen1(navController)
        }
        composable (route = NavRoutes.OnBoarding2.route){
            OnBoardingScreen2(navController)
        }
        composable (route = NavRoutes.OnBoarding3.route){
            OnBoardingScreen3(navController)
        }

        composable (route = NavRoutes.Sections.route){
            SectionsScreen()
        }



    }



}