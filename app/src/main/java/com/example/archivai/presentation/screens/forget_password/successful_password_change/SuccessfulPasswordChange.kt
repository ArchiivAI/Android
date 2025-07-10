package com.example.archivai.presentation.screens.forget_password.successful_password_change

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.archivai.R
import com.example.archivai.presentation.navigation.Screens
import com.example.archivai.presentation.screens.login_screen.composables.ImageContainer
import com.example.archivai.presentation.screens.login_screen.composables.Spacer10
import com.example.archivai.presentation.screens.login_screen.composables.Spacer32
import com.example.archivai.presentation.screens.login_screen.composables.Spacer72
import com.example.archivai.presentation.theme.play_fair_font
import kotlinx.coroutines.delay

@Composable
fun SuccessfulPasswordChange(navController: NavController) {
    var countdown by remember { mutableStateOf(3) }
    var showCountdown by remember { mutableStateOf(false) }

    // Handle timing logic
    LaunchedEffect(Unit) {
        delay(2000) // Wait 2 seconds
        showCountdown = true
        for (i in 3 downTo 1) {
            countdown = i
            delay(1000)
        }
        navController.navigate(Screens.Login) {
            popUpTo(Screens.SuccessfulPassword) { inclusive = true }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageContainer(R.drawable.shield_with_lock)
        Spacer72()
        Text(
            text = "Your Password Changed Successfully!",
            textAlign = TextAlign.Center,
            color = Color(0XFF132863),
            fontSize = 18.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Normal,
            fontFamily = play_fair_font
        )
        Spacer32()

        if (showCountdown) {
            Text(
                text = "Redirecting to Login in $countdown...",
                textAlign = TextAlign.Center,
                color = Color.Gray,
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Light,
                fontFamily = play_fair_font
            )
            Spacer10()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SuccessfulPasswordChangePreview(modifier: Modifier = Modifier) {
    SuccessfulPasswordChange(rememberNavController())
}