package com.example.archivai.presentation.screens.folders.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.archivai.R
import com.example.archivai.presentation.theme.AppColor
import com.example.archivai.presentation.theme.rubik_medium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadFileOptionsBottomSheet() {
    ModalBottomSheet(
        onDismissRequest = { },
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        containerColor = Color(0xFFF6F8FF)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = rememberAsyncImagePainter(R.drawable.this_device),
                    contentDescription = "this device",
                    tint = AppColor,
                    modifier = Modifier
                        .size(48.dp)
                        .clickable { /* Handle click */ }
                )
                Spacer(modifier = Modifier.size(6.dp))
                Text(
                    text = "This Device",
                    fontFamily = rubik_medium,
                    fontSize = 14.sp, color = AppColor
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(R.drawable.take_photo),
                    contentDescription = "take photo",
                    tint = AppColor,
                    modifier = Modifier
                        .size(48.dp)
                        .clickable { /* Handle click */ }
                )
                Spacer(modifier = Modifier.size(6.dp))
                Text(
                    text = "Take Photo",
                    fontFamily = rubik_medium,
                    fontSize = 14.sp, color = AppColor
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun UploadFileOptionsBottomSheetPreview() {
    UploadFileOptionsBottomSheet()
}




