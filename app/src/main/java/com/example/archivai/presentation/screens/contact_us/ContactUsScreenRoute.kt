package com.example.archivai.presentation.screens.contact_us

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.archivai.presentation.navigation.Screens
import com.example.archivai.presentation.screens.activity_log.ActivityLogScreen

fun NavGraphBuilder.contactUsScreenRoute(navController: NavController){

    composable <Screens.ContactUs> {
        ContactUsScreen(navController)
    }

}