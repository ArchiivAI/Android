package com.example.archivai.presentation.screens.forget_password.forget_password

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.archivai.R
import com.example.archivai.presentation.navigation.Screens
import com.example.archivai.presentation.screens.contact_us.UiState
import com.example.archivai.presentation.screens.login_screen.composables.EmailContainer
import com.example.archivai.presentation.screens.login_screen.composables.ImageContainer
import com.example.archivai.presentation.screens.login_screen.composables.RectangleButton
import com.example.archivai.presentation.screens.login_screen.composables.Spacer10
import com.example.archivai.presentation.screens.login_screen.composables.Spacer16
import com.example.archivai.presentation.screens.login_screen.composables.Spacer32
import com.example.archivai.presentation.screens.login_screen.composables.Spacer64
import com.example.archivai.presentation.screens.login_screen.composables.TopText

import com.example.archivai.presentation.theme.play_fair_font

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgetPasswordScreen(navController: NavController , viewModel : ForgetPasswordViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()
    var email by remember { mutableStateOf("")}
    val context = LocalContext.current

    LaunchedEffect(state) {
        when (state) {
            is ForgetPasswordUiState.Success -> {
                navController.navigate(Screens.OtpVerify(email))
                Toast.makeText(context, (state as ForgetPasswordUiState.Success).message, Toast.LENGTH_SHORT).show()

            }
            is ForgetPasswordUiState.Error -> {
                Toast.makeText(context, (state as ForgetPasswordUiState.Error).message, Toast.LENGTH_SHORT).show()
            }
            else -> Unit
        }
    }



    Column(
        modifier = Modifier.fillMaxSize()
            .padding(vertical = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Icon(
                painterResource(R.drawable.arrow_icon),
                contentDescription = "back icon",
                modifier = Modifier
                    .clickable {}
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 6.dp)
                    .size(20.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            TopText("Find Your Account")
        }
        Spacer64()
        ImageContainer(R.drawable.chatgpt_robot_holding_loupe)
        Spacer32()
        Spacer10()
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = {
                Text(
                    text = "Email",
                    color = Color(0xFF132863),
                    fontFamily = play_fair_font,
                    fontWeight = FontWeight.Normal
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null,
                    tint = Color(0XFF132863)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF132863),
                unfocusedBorderColor = Color(0xFF132863)
            )


        )
        Spacer16()
        Text(
            text = "You may receive Mobile notifications \n" +
                    "for security and login purposes.",
            textAlign = TextAlign.Center,
            color = Color(0XFF132863),
            fontSize = 18.sp,

            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Normal,
            fontFamily = play_fair_font

        )
        Spacer64()
        RectangleButton("Send") { viewModel.sendChangePasswordMail(email) }


    }





    
}
@Preview(showBackground = true)
@Composable
fun ForgetPasswordScreenPreview(modifier: Modifier = Modifier) {
    ForgetPasswordScreen(navController = rememberNavController())

}