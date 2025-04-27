package com.example.archivai.presentation.screens.folders

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.archivai.presentation.navigation.Screens
import com.example.archivai.presentation.screens.activity_log.ActivityLogScreen

fun NavGraphBuilder.foldersScreenRoute(navController: NavController){

    composable <Screens.Folders> {
        FoldersScreen(navController)
    }

}