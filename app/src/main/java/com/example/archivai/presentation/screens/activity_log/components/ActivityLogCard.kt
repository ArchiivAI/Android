package com.example.archivai.presentation.screens.activity_log.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.archivai.R
import com.example.archivai.presentation.theme.rubik_regular

@Composable
fun ActivityLogCard(userImage : String , body : String , date : String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(4.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFE0E0E0),
                shape = RoundedCornerShape(8.dp)
            ).padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = userImage .ifEmpty { R.drawable.image_placeholder },
            contentDescription = "",
            modifier = Modifier.size(40.dp)
                .clip(CircleShape)

        )

        Spacer(modifier = Modifier.width(16.dp))

        // Text content
        Column {
            Text(
                text = body,
                fontSize = 14.sp,
                fontFamily = rubik_regular,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(2.dp))

            // Timestamp
            Text(
                text = date,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActivityLogCardPreview() {
    ActivityLogCard("","" , "")
}