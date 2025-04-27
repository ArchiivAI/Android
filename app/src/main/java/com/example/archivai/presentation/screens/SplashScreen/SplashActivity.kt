package com.example.archivai.presentation.screens.SplashScreen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.archivai.presentation.screens.OnBoardingScreens.OnBoardingScreens
import com.example.archivai.presentation.theme.ArchivAITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ArchivAITheme {
                OnBoardingScreens(rememberNavController())

            }

        }
    }

}