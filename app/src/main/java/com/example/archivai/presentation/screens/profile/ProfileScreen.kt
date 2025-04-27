package com.example.archivai.presentation.screens.profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.archivai.R
import com.example.archivai.presentation.theme.AppColor
import com.example.archivai.presentation.theme.rubik_medium
import com.example.archivai.presentation.theme.rubik_regular
import com.example.archivai.presentation.theme.rubik_semibold

@Composable
fun ProfileScreen(
    navController: NavController
) {
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
            Image(
                painter = painterResource(id = android.R.drawable.ic_menu_camera),
                contentDescription = "Camera Icon",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // User Name
        Text(
            text = "Ahmed Ali",
            fontSize = 20.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(32.dp))

        // List Items
        ProfileListItem(
            icon = R.drawable.pencil_edit,
            text = "Personal Info",
            onClick = {  }
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
            onClick = {  }
        )
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