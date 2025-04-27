package com.example.archivai.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.archivai.R

@Composable
fun HomeTopAppBar(userName: String, onSearchClick: () -> Unit = {}, onProfileClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Welcome Back,",
                fontSize = 16.sp,
                color = Color(0xFF1A237E) // Dark Blue
            )
            Text(
                text = userName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A237E)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painterResource(R.drawable.search_icon),
                contentDescription = "Search",
                tint = Color(0xFF1A237E),
                modifier = Modifier
                    .clickable { onSearchClick() }
                    .padding(end = 16.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.image_placeholder), // Make sure you have this image
                contentDescription = "Profile",
                modifier = Modifier
                    .height(36.dp)
                    .clickable { onProfileClick() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeTopAppBarPreview() {
    HomeTopAppBar("Ahmed")
}
