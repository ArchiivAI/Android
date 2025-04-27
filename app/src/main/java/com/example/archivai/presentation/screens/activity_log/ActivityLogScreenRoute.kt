package com.example.archivai.presentation.screens.activity_log

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.archivai.presentation.navigation.Screens

fun NavGraphBuilder.activityLogScreenRoute(navController: NavController){

    composable <Screens.ActivityLog> {
        ActivityLogScreen(navController)
    }

}