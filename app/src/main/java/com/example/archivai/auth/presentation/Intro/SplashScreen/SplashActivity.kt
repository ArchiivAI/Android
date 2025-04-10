package com.example.archivai.auth.presentation.Intro.SplashScreen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.archivai.auth.presentation.ui.screens.LoginScreen

import com.example.ui.theme.ArchivAITheme
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