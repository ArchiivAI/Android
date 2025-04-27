package com.example.archivai.presentation.screens.employees.add_new_employee

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.archivai.presentation.navigation.Screens
import com.example.archivai.presentation.screens.activity_log.ActivityLogScreen
import com.example.archivai.presentation.screens.folders.FoldersScreen
import com.example.archivai.roles.presentation.AddNewEmployeeScreen

fun NavGraphBuilder.addNewEmployeeScreenRoute(navController: NavController){

    composable <Screens.AddNewEmployee> {
        AddNewEmployeeScreen(navController)
    }

}