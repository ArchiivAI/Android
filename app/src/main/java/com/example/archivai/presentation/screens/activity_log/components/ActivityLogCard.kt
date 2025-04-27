package com.example.archivai.presentation.screens.activity_log.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.archivai.R
import com.example.archivai.presentation.theme.rubik_regular
import com.example.archivai.presentation.theme.rubik_semibold

@Composable
fun ActivityLogCard(name : String , body : String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(4.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFE0E0E0), // Light gray outline
                shape = RoundedCornerShape(8.dp)
            ).padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar placeholder
        Image(
            painter = painterResource(R.drawable.image_placeholder),
            contentDescription = "",
            modifier = Modifier.size(40.dp)

        )

        Spacer(modifier = Modifier.width(12.dp))

        // Text content
        Column {
            // Name (bold) and action text
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontFamily = rubik_semibold)) {
                        append(name)
                    }
                    append(" $body")
                },
                fontSize = 14.sp,
                fontFamily = rubik_regular,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Timestamp
            Text(
                text = "20 Mins ago",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActivityLogCardPreview() {
    ActivityLogCard("","")
}