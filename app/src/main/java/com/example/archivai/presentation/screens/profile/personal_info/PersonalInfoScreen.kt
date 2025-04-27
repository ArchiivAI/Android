package com.example.archivai.presentation.screens.profile.personal_info


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.archivai.R
import com.example.archivai.presentation.screens.login_screen.composables.Spacer16
import com.example.archivai.presentation.screens.login_screen.composables.Spacer24
import com.example.archivai.presentation.theme.AppColor
import com.example.archivai.presentation.theme.rubik_medium
import com.example.archivai.presentation.theme.rubik_semibold

@Composable
fun PersonalInfoScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 48.dp, start = 24.dp, end = 24.dp)
                .padding(bottom = 72.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
            ) {
                Icon(
                    painterResource(R.drawable.arrow_icon),
                    contentDescription = "back icon",
                    modifier = Modifier
                        .clickable {}
                        .align(Alignment.CenterVertically)
                        .padding(6.dp)
                )



            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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


                // Role Name Field
                Column {
                    Text(
                        text = "First Name",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = rubik_medium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    BasicTextField(
                        value = "Ahmed",
                        onValueChange = { /* Handle input change */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, shape = RoundedCornerShape(8.dp))
                            .border(1.dp, AppColor, RoundedCornerShape(8.dp))
                            .padding(12.dp),
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black
                        ),
                        singleLine = true
                    )
                }
                Column {
                    Text(
                        text = "last Name",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = rubik_medium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    BasicTextField(
                        value = "Ali",
                        onValueChange = { /* Handle input change */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, shape = RoundedCornerShape(8.dp))
                            .border(1.dp, AppColor, RoundedCornerShape(8.dp))
                            .padding(12.dp),
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black
                        ),
                        singleLine = true
                    )
                }

                Column {
                    Text(
                        text = "Email",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = rubik_medium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    BasicTextField(
                        value = "ahmedali11@archivai.net",
                        onValueChange = { /* Handle input change */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, shape = RoundedCornerShape(8.dp))
                            .border(1.dp, AppColor, RoundedCornerShape(8.dp))
                            .padding(12.dp),
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black
                        ),
                        singleLine = true
                    )
                }

                Spacer16()
                Button(
                    onClick = { /* Handle button click */ },
                    modifier = Modifier

                        .height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColor, // Blue color from the image
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Change Password",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = rubik_medium
                    )
                }


            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun PersonalInfoScreenPreview() {
    PersonalInfoScreen(rememberNavController())
}