package com.example.archivai.presentation.screens.login_screen.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.archivai.presentation.theme.rubik_bold
import com.example.archivai.presentation.theme.rubik_semibold

@Composable
fun RectangleButton(text : String,onClick : ()-> Unit) {
    Button(onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(48.dp).padding(start=24.dp,end =24.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF132863))
    ) {
        Text(text = text,fontSize= 18.sp, color = Color.White , fontFamily = rubik_semibold)

    }

}