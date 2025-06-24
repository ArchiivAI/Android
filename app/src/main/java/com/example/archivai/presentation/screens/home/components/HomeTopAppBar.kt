package com.example.archivai.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.archivai.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeTopAppBar(userName: String, onSearchClick: () -> Unit = {}, onProfileClick: () -> Unit = {}, imageUrl : String? = null) {
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

            val safeImageUrl = imageUrl?.replace(" ", "%20") ?: R.drawable.image_placeholder
            GlideImage(
                model = safeImageUrl,
                contentDescription = "Profile",
                modifier = Modifier
                    .size(36.dp)
                    .clickable { onProfileClick() },
                contentScale = ContentScale.Crop
            ) {
                it.error(R.drawable.image_placeholder)
                    .placeholder(R.drawable.image_placeholder)
                    .circleCrop()
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeTopAppBarPreview() {
    HomeTopAppBar("Ahmed")
}
