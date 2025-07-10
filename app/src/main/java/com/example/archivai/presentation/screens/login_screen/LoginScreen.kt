package com.example.archivai.presentation.screens.login_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.archivai.R
import com.example.archivai.data.utils.SharedPrefsHelper
import com.example.archivai.presentation.navigation.Screens
import com.example.archivai.presentation.screens.login_screen.composables.ImageContainer
import com.example.archivai.presentation.screens.login_screen.composables.Spacer10
import com.example.archivai.presentation.screens.login_screen.composables.Spacer16
import com.example.archivai.presentation.screens.login_screen.composables.Spacer24
import com.example.archivai.presentation.screens.login_screen.composables.Spacer32
import com.example.archivai.presentation.screens.login_screen.composables.TopText
import com.example.archivai.presentation.theme.AppColor
import com.example.archivai.presentation.theme.play_fair_font
import com.example.archivai.presentation.theme.rubik_semibold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState
    var context = LocalContext.current
    LaunchedEffect(uiState) {
        if (uiState.isLoginSuccessful) {
            Toast.makeText(context, "Logged in Successfully", Toast.LENGTH_SHORT).show()
            navController.navigate(Screens.Home) {
                popUpTo(Screens.Login) { inclusive = true }
            }
            Log.d("tk", SharedPrefsHelper.getToken().toString() )
            viewModel.resetLoginState()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer32()
        TopText("Welcome To ArchivAI")
        Spacer32()
        ImageContainer(R.drawable.archive_ai_logo)

        OutlinedTextField(
            value = uiState.username,
            onValueChange = { viewModel.onUsernameChanged(it) },
            label = {
                Text(
                    text = "Email",
                    color = Color(0xFF132863),
                    fontFamily = play_fair_font,
                    fontWeight = FontWeight.Normal
                )
            },
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
            )


        )
        Spacer16()
        OutlinedTextField(
            value = uiState.password,
            onValueChange = { viewModel.onPasswordChanged(it) },
            label = {
                Text(
                    text = "Password",
                    color = Color(0xFF132863),
                    fontFamily = play_fair_font,
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
                IconButton(onClick = { viewModel.onPasswordVisibilityChanged() }) {
                    Icon(
                        imageVector = if (uiState.passwordVisible)
                            Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                        tint = AppColor,
                        contentDescription = null
                    )
                }
            },
            visualTransformation = if (uiState.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        )
        Spacer10()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = uiState.rememberMe,
                    onCheckedChange = { viewModel.onRememberMeChanged(it) },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0XFF132863), // Checkbox fill color when checked
                        uncheckedColor = Color(0XFF132863), // Background color when unchecked
                        checkmarkColor = Color(0XFF132863), // Checkmark color
                        disabledUncheckedColor = Color(0XFF132863)
                    )
                )
                Text(
                    text = "Remember me",
                    fontFamily = play_fair_font,
                    fontSize = 16.sp,
                    color = Color.Gray
                )


            }

            TextButton(onClick = { navController.navigate(Screens.ForgetPassword) }) {
                Text(
                    text = "Forgot password?",
                    fontFamily = play_fair_font,
                    fontSize = 16.sp,
                    style = TextStyle(textDecoration = TextDecoration.Underline),
                    color = Color(0XFF132863)
                )
            }


        }
        Spacer32()
        Button(
            onClick = { viewModel.login() },
            modifier = Modifier.fillMaxWidth().height(48.dp).padding(start = 24.dp, end = 24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF132863))
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White,
                )
            } else {
                Text(
                    text = "Login",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontFamily = rubik_semibold
                )
            }

        }
            Spacer24()
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Don't have an account?",
                    fontSize = 16.sp,
                    fontFamily = play_fair_font,
                    fontWeight = FontWeight.Normal,
                    color = Color.DarkGray
                )
                TextButton(onClick = { navController.navigate(Screens.ContactUs) }) {
                    Text(
                        text = "Contact us",
                        fontSize = 20.sp,
                        textDecoration = TextDecoration.Underline,
                        fontFamily = play_fair_font,
                        color = Color(0XFF132863)
                    )


                }


            }


        }


    }


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(modifier: Modifier = Modifier) {
    //  LoginScreen(rememberNavController())

}