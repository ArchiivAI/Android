package com.example.archivai.presentation.screens.folders.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.archivai.R
import com.example.archivai.presentation.screens.folders.utils.dashedBorder
import com.example.archivai.presentation.theme.rubik_medium
import java.io.File

@Composable
fun AddNewFileButton(
    imagePath: String,
    onClick: () -> Unit,
    imageSelected: Boolean,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val imageRequest = remember(imagePath) {
        val data = when {
            imagePath.toIntOrNull() != null -> imagePath.toInt()
            else -> File(imagePath)
        }
        ImageRequest.Builder(context)
            .data(data)
            .crossfade(true)
            .build()
    }
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .dashedBorder(
                color = Color(0x1F1F1F1F),
                shape = RoundedCornerShape(16.dp),
                strokeWidth = 1.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        if (imageSelected) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x14000000)), contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    modifier = Modifier
                        .background(color = Color.Transparent, shape = RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.FillBounds,
                    model = imageRequest,
                    contentDescription = null,
                )
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFFFFFFF)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.edit_pencil_filled),
                        contentDescription = "Edit",
                        tint = Color(0xFFF49061),
                        )
                }
            }
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .size(22.dp)
                            .padding(1.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.upload_image),
                        contentDescription = "Upload",
                        tint = Color(0x611F1F1F),
                    )
                }
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Upload",
                    style = TextStyle(
                        fontFamily = rubik_medium,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        lineHeight = 17.sp
                    ),
                    color = Color(0x611F1F1F),

                    )
            }
        }
    }
}

@Preview
@Composable
fun AddNewFileButtonPreview() {
    AddNewFileButton(
        imagePath = "https://example.com/image.jpg",
        onClick = {},
        imageSelected = false,
        modifier = Modifier.size(100.dp)
    )
}

