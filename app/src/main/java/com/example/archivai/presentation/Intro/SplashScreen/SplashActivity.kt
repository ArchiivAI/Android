package com.example.archivai.presentation.Intro.SplashScreen

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.archivai.presentation.auth.ui.LoginScreen
import com.example.archivai.presentation.ui.theme.ArchivAITheme

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