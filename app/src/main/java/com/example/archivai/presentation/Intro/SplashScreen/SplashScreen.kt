package com.example.archivai.presentation.Intro.SplashScreen

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.archivai.R
import com.example.archivai.presentation.MainActivity
import com.example.archivai.presentation.ui.theme.play_fair_font
import kotlinx.coroutines.delay


@Composable
fun SplashScreen() {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        delay(2000)
        context.startActivity(Intent(context, MainActivity::class.java))

    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.dashboard),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        Box(
            modifier = Modifier.fillMaxSize().background(
                color = Color(0xFF132863)
                    .copy(alpha = 0.82F)
            ), contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                Arrangement.Center,
                Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Welcome to ArchivAI !",
                    fontSize = 24.sp, color = Color.White,
                    fontFamily = play_fair_font, fontStyle = FontStyle.Normal
                )
                Spacer(modifier = Modifier.height(32.dp))
                Image(
                    painter = painterResource(R.drawable.archive_ai_logo),
                    contentDescription = "App logo", modifier = Modifier.size(150.dp)
                )


            }
        }
    }
}
@Preview
@Composable
fun SplashScreenPreview (modifier: Modifier = Modifier) {

    SplashScreen()
}