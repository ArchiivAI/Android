package com.example.archivai

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.archivai.core.navigation.AppNavGraph

@Composable
fun Main(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    AppNavGraph(navController = navController)


}