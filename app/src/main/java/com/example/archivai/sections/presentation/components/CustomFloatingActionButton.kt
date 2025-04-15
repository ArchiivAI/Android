package com.example.archivai.sections.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.archivai.R


@Composable
fun CustomFloatingActionButton(onClick: @Composable () -> Unit, modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFCDAD8F),
                            Color(0xFFFAE1CB)

                        )
                    )
                )
                .clickable { onClick },
            contentAlignment = Alignment.Center

        ) {
            Icon(painterResource(R.drawable.add_icon), contentDescription = "add button",tint = Color.Black)

        }

    }


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomFloatingActionButtonPreview(modifier: Modifier = Modifier) {

}