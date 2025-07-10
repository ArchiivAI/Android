package com.example.archivai.presentation.screens.forget_password.otp_verify



import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.archivai.R
import com.example.archivai.presentation.navigation.Screens
import com.example.archivai.presentation.screens.login_screen.composables.ImageContainer
import com.example.archivai.presentation.screens.login_screen.composables.Spacer10
import com.example.archivai.presentation.screens.login_screen.composables.Spacer16
import com.example.archivai.presentation.screens.login_screen.composables.Spacer32
import com.example.archivai.presentation.screens.login_screen.composables.Spacer64
import com.example.archivai.presentation.screens.login_screen.composables.TopText
import com.example.archivai.presentation.theme.rubik_medium
import com.example.archivai.presentation.theme.rubik_semibold

@Composable
fun OtpVerifyScreen(navController: NavController , email : String , viewModel: OtpVerifyViewModel = hiltViewModel()) {
    val otpValues = remember { mutableStateOf(List(5) { "" }) }
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val otpCode = otpValues.value.joinToString(separator = "")

    LaunchedEffect(state) {
        when (state) {
            is OtpVerifyUiState.Success -> {
                navController.navigate(Screens.NewPassword(email,otpCode))
                Toast.makeText(context, (state as OtpVerifyUiState.Success).message, Toast.LENGTH_SHORT).show()
            }
            is OtpVerifyUiState.Error -> {
                Toast.makeText(context, (state as OtpVerifyUiState.Error).message, Toast.LENGTH_SHORT).show()
            }
            else -> Unit
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Icon(
                painterResource(R.drawable.arrow_icon),
                contentDescription = "back icon",
                modifier = Modifier
                    .clickable {navController.popBackStack()}
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 6.dp)
                    .size(20.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            TopText("Verify Your Email")
        }
        Spacer64()
        Text(
            text = "Please enter the 5 digit code sent to your email",
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontSize = 18.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Normal,
            fontFamily = rubik_medium
        )
        ImageContainer(R.drawable.chatbot_using_laptop)
        Spacer32()
        Spacer10()
        Spacer16()
        Text(
            text = "OTP Code",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 32.dp),
            color = Color.Gray,
            fontSize = 16.sp,
        )
        Spacer16()

        OtpInputField(
            otpValues = otpValues.value,
            onOtpChange = { otpValues.value = it }
        )

        Spacer32()

        Button(
            onClick = { viewModel.verifyOtp(otpCode,email) },
            modifier = Modifier.fillMaxWidth().height(48.dp).padding(start = 24.dp, end = 24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF132863))
        ) {
            if (state is OtpVerifyUiState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White,
                )
            } else {
                Text(
                    text = "Send",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontFamily = rubik_semibold
                )
            }

        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OtpInputField(
    otpValues: List<String>,
    onOtpChange: (List<String>) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val focusRequesters = remember { List(otpValues.size) { FocusRequester() } }

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        otpValues.forEachIndexed { index, value ->
            OutlinedTextField(
                value = value,
                onValueChange = { input ->
                    if (input.length <= 1) {
                        val newOtp = otpValues.toMutableList()
                        newOtp[index] = input
                        onOtpChange(newOtp)

                        if (input.isNotEmpty()) {
                            // Move focus to next input
                            if (index < otpValues.lastIndex) {
                                focusRequesters[index + 1].requestFocus()
                            } else {
                                focusManager.clearFocus() // Last box, hide keyboard
                            }
                        }
                    }
                }, shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .size(56.dp)
                    .focusRequester(focusRequesters[index]),
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
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
                visualTransformation = VisualTransformation.None
            )
        }
    }
}


