package com.example.archivai.auth.presentation.ui.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.ui.theme.play_fair_font

@Composable
fun TopText(text : String) {
    Text(text = text,
        fontSize = 32.sp ,
        color = Color(0XFF132863) ,
        fontFamily = play_fair_font ,
        fontWeight = FontWeight.Normal)

}