package com.example.archivai.presentation.screens.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.archivai.presentation.navigation.Screens
import com.example.archivai.presentation.screens.activity_log.ActivityLogScreen
import com.example.archivai.presentation.screens.folders.FoldersScreen

fun NavGraphBuilder.homeScreenRoute(navController: NavController){

    composable <Screens.Home> {
        HomeScreen(navController)
    }

}