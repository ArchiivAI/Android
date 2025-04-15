package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.example.archivai.auth.presentation.Intro.OnBoardingScreens.OnBoardingScreen1
import com.example.archivai.auth.presentation.Intro.SplashScreen.SplashScreen
import com.example.archivai.auth.presentation.ui.screens.ContactUsScreen
import com.example.archivai.auth.presentation.ui.screens.NewPasswordScreen
import com.example.archivai.auth.presentation.ui.screens.NewPasswordScreenPreview
import com.example.archivai.core.navigation.AppNavGraph
import com.example.archivai.sections.presentation.SectionsScreen
import com.example.ui.theme.ArchivAITheme


class MainActivity : ComponentActivity() {
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArchivAITheme {
                val navController = rememberNavController()
                AppNavGraph(navController = navController)




            }
        }
    }
}

