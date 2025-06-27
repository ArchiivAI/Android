package com.example.archivai.presentation.screens.folders

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.archivai.presentation.navigation.Screens

fun NavGraphBuilder.foldersScreenRoute(navController: NavController) {
    composable<Screens.Folders> { backStackEntry ->
        val args = backStackEntry.toRoute<Screens.Folders>()
        FoldersScreen(
            navController = navController,
            sectionId = args.sectionId,
            folderId = args.folderId,
            sectionName = args.sectionName,
            folderName = args.folderName
        )
    }
}