package com.example.archivai.presentation.screens.forget_password.new_password

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.archivai.R
import com.example.archivai.presentation.navigation.Screens
import com.example.archivai.presentation.screens.login_screen.composables.ImageContainer
import com.example.archivai.presentation.screens.login_screen.composables.Spacer16
import com.example.archivai.presentation.screens.login_screen.composables.Spacer50
import com.example.archivai.presentation.screens.login_screen.composables.TopText
import com.example.archivai.presentation.theme.AppColor
import com.example.archivai.presentation.theme.rubik_medium
import com.example.archivai.presentation.theme.rubik_regular

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPasswordScreen(navController: NavController, email : String, otp : String , viewModel: NewPasswordViewModel = hiltViewModel()) {

    var password by remember { mutableStateOf("") }
    var passwordConfirmation by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(state) {
        when (state) {
            is NewPasswordUiState.Success -> {
                navController.navigate(Screens.SuccessfulPassword)
                Toast.makeText(context, (state as NewPasswordUiState.Success).message, Toast.LENGTH_SHORT).show()
            }
            is NewPasswordUiState.Error -> {
                Toast.makeText(context, (state as NewPasswordUiState.Error).message, Toast.LENGTH_SHORT).show()
            }
            else -> Unit
        }
    }







    Column(
        modifier = Modifier.fillMaxSize()
            .padding(vertical = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
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
            TopText("Create New Password")
        }

        Spacer50()
        ImageContainer(R.drawable.small_chatbot_with_locked_padlock)
        Spacer50()
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text(
                    text = "New Password",
                    color = Color(0xFF132863),
                    fontFamily = rubik_regular,
                    fontWeight = FontWeight.Normal
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = Color(0XFF132863)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF132863),
                unfocusedBorderColor = Color(0xFF132863),
                cursorColor = Color(0xFF132863),
                focusedTextColor = Color(0xFF132863),
                unfocusedTextColor = Color(0xFF132863),
                focusedLeadingIconColor = Color(0xFF132863),
                unfocusedLeadingIconColor = Color(0xFF132863),
                focusedTrailingIconColor = Color(0xFF132863),
                unfocusedTrailingIconColor = Color(0xFF132863),
                focusedLabelColor = Color(0xFF132863),
                unfocusedLabelColor = Color(0xFF132863)
            ),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible)
                            Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                        tint = AppColor,
                        contentDescription = null
                    )
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        )
        Spacer16()
        OutlinedTextField(
            value = passwordConfirmation,
            onValueChange = { passwordConfirmation = it },
            label = {
                Text(
                    text = "Confirm Password",
                    color = Color(0xFF132863),
                    fontFamily = rubik_regular,
                    fontWeight = FontWeight.Normal
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = Color(0XFF132863)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF132863),
                unfocusedBorderColor = Color(0xFF132863),
                cursorColor = Color(0xFF132863),
                focusedTextColor = Color(0xFF132863),
                unfocusedTextColor = Color(0xFF132863),
                focusedLeadingIconColor = Color(0xFF132863),
                unfocusedLeadingIconColor = Color(0xFF132863),
                focusedTrailingIconColor = Color(0xFF132863),
                unfocusedTrailingIconColor = Color(0xFF132863),
                focusedLabelColor = Color(0xFF132863),
                unfocusedLabelColor = Color(0xFF132863)
            ),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible)
                            Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                        tint = AppColor,
                        contentDescription = null
                    )
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        )
        Spacer16()
        Button(
            onClick = { viewModel.createNewPassword(password,passwordConfirmation,email,otp) },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppColor, // Blue color from the image
                contentColor = Color.White
            )
        ) {
            if ( state is NewPasswordUiState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White
                )
            } else {
                Text(
                    text = "Create",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = rubik_medium
                )
            }

        }



    }

}

//@Preview(showBackground = true)
//@Composable
//fun NewPasswordScreenPreview() {
//    NewPasswordScreen(rememberNavController())
//}