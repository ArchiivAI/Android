package com.example.archivai.presentation.screens.forget_password.successful_password_change

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.archivai.presentation.screens.login_screen.composables.ImageContainer
import com.example.archivai.presentation.screens.login_screen.composables.RectangleButton
import com.example.archivai.presentation.screens.login_screen.composables.Spacer10
import com.example.archivai.presentation.screens.login_screen.composables.Spacer32
import com.example.archivai.presentation.screens.login_screen.composables.Spacer72

import com.example.archivai.presentation.theme.play_fair_font

@Composable
fun SuccessfulPasswordChange(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        ImageContainer(R.drawable.shield_with_lock)
        Spacer72()
        Text(
            text = "Your Password Changed Successfully !",
            textAlign = TextAlign.Center,
            color = Color(0XFF132863),
            fontSize = 18.sp,

            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Normal,
            fontFamily = play_fair_font

        )
        Spacer32()
        Spacer10()
        RectangleButton("Go to Home",{})




    }
}

@Preview(showBackground = true)
@Composable
fun SuccessfulPasswordChangePreview(modifier: Modifier = Modifier) {
    SuccessfulPasswordChange(rememberNavController())
}