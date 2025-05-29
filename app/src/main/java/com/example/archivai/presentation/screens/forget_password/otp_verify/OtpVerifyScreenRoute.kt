package com.example.archivai.presentation.screens.forget_password.otp_verify

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.archivai.presentation.navigation.Screens


fun NavGraphBuilder.otpVerifyScreenRoute(navController: NavController){

    composable <Screens.OtpVerify> { backStackEntry ->
        val email = backStackEntry.arguments?.getString("email") ?: ""
        OtpVerifyScreen(navController, email)
    }

}