package com.example.archivai.presentation.screens.login_screen.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.archivai.R

@Composable
fun ImageContainer(Imgsource :Int ) {
    Image(painter = painterResource(Imgsource), contentDescription = null
        , modifier = Modifier.size(240.dp))

}