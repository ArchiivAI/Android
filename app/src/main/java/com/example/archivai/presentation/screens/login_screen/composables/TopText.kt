package com.example.archivai.presentation.screens.login_screen.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.archivai.presentation.theme.play_fair_font
import com.example.archivai.presentation.theme.rubik_semibold

@Composable
fun TopText(text : String) {
    Text(text = text,
        fontSize = 20.sp ,
        color = Color(0XFF132863) ,
        fontFamily = rubik_semibold,
        fontWeight = FontWeight.Normal)

}