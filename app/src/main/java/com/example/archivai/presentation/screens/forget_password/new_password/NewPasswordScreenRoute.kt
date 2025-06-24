package com.example.archivai.presentation.screens.forget_password.new_password

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.archivai.presentation.navigation.Screens

fun NavGraphBuilder.newPasswordScreenRoute(navController: NavController){

    composable <Screens.NewPassword> { backStackEntry ->
        val email = backStackEntry.arguments?.getString("email") ?: ""
        val otp = backStackEntry.arguments?.getString("otp") ?: ""
        NewPasswordScreen(navController, email,otp)
    }

}