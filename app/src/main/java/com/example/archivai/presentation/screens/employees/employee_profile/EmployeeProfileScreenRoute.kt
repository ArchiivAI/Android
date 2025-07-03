package com.example.archivai.presentation.screens.employees.employee_profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.archivai.presentation.navigation.Screens

fun NavGraphBuilder.employeeProfileScreenRoute(
    navController: NavController
) {
    composable <Screens.EmployeeProfile> {
        EmployeeProfileScreen(navController)
    }
}