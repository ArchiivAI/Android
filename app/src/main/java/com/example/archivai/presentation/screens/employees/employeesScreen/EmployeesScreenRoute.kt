package com.example.archivai.presentation.screens.employees.employeesScreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.archivai.presentation.navigation.Screens

fun NavGraphBuilder.employeesScreenRoute(navController: NavController){

    composable <Screens.Employees> {
        EmployeeScreen(navController)
    }

}