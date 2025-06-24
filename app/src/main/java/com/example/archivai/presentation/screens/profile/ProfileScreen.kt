package com.example.archivai.presentation.screens.profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.archivai.R
import com.example.archivai.presentation.navigation.Screens
import com.example.archivai.presentation.screens.profile.components.LogOutDialog
import com.example.archivai.presentation.theme.AppColor
import com.example.archivai.presentation.theme.rubik_medium

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        // Profile Picture
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        ) {
            val safeImageUrl = state.imageUrl?.replace(" ", "%20") ?: R.drawable.image_placeholder
            GlideImage(
                model = safeImageUrl,
                contentDescription = "Profile",
                contentScale = ContentScale.Crop // Crop to fit
            ) {
                it.error(R.drawable.image_placeholder) // Fallback if URL fails
                    .placeholder(R.drawable.image_placeholder) // Shown while loading
                    .circleCrop() // Optional: Apply circular crop for profile picture
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // User Name
        Text(
            text = state.userName,
            fontSize = 20.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(32.dp))

        // List Items
        ProfileListItem(
            icon = R.drawable.pencil_edit,
            text = "Personal Info",
            onClick = { }
        )

        Divider(color = Color.LightGray, thickness = 1.dp)

        ProfileListItem(
            icon = R.drawable.hep_center,
            text = "Help Center",
            onClick = { }
        )

        Divider(color = Color.LightGray, thickness = 1.dp)

        ProfileListItem(
            icon = R.drawable.log_out,
            text = "Log Out",
            onClick = {
                viewModel.showLogOutDialog()
            }
        )
        if (state.showLogOutDialog){
            LogOutDialog(
              onDismiss = {
                  viewModel.hideLogOutDialog()
              },
                onConfirm = {
                    viewModel.logOut {
                        navController.navigate(Screens.Login) {
                            popUpTo(0) { inclusive = true }
                            launchSingleTop = true

                        }
                    }

                }


            )

        }


        if (state.isLoggingOut) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun ProfileListItem(
    icon: Int,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = AppColor
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = text,
            fontSize = 16.sp,
            fontFamily = rubik_medium,
            color = AppColor,
            modifier = Modifier.weight(1f)
        )

        Icon(
            painter = painterResource(id = R.drawable.arrow_right),
            contentDescription = "Chevron Right",
            modifier = Modifier.size(20.dp),
            tint = AppColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        rememberNavController()
    )
}