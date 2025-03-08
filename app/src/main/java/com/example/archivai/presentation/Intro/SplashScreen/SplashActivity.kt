package com.example.archivai.presentation.Intro.SplashScreen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.archivai.presentation.auth.ui.screens.LoginScreen
import com.example.archivai.presentation.ui.theme.ArchivAITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ArchivAITheme {
                LoginScreen()

            }

        }
    }

}