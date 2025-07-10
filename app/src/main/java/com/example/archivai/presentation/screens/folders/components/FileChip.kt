package com.example.archivai.presentation.screens.folders.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import com.example.archivai.presentation.theme.AppColor
import com.example.archivai.presentation.theme.rubik_medium
import java.io.File

@Composable
fun FileChip(
    file: File,
    progress: Int? = null,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFE2E8F0))
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = file.name,
                fontSize = 14.sp,
                color = Color.Black,
                fontFamily = rubik_medium,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Remove file",
                tint = AppColor,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onRemove() }
            )
        }

        if (progress != null && progress in 0..99) {
            LinearProgressIndicator(
                progress = progress / 100f,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                color = AppColor,
                trackColor = Color.LightGray
            )
        }
    }
}
