package com.example.archivai.presentation.screens.forget_password.otp_verify



import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.archivai.R
import com.example.archivai.presentation.screens.login_screen.composables.ImageContainer
import com.example.archivai.presentation.screens.login_screen.composables.RectangleButton
import com.example.archivai.presentation.screens.login_screen.composables.Spacer10
import com.example.archivai.presentation.screens.login_screen.composables.Spacer16
import com.example.archivai.presentation.screens.login_screen.composables.Spacer32
import com.example.archivai.presentation.screens.login_screen.composables.Spacer64
import com.example.archivai.presentation.screens.login_screen.composables.TopText
import com.example.archivai.presentation.theme.AppColor
import com.example.archivai.presentation.theme.rubik_medium

@Composable
fun OtpVerifyScreen(navController: NavController) {
    val otpValues = remember { mutableStateOf(List(5) { "" }) }

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
                    .clickable {}
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

        RectangleButton("Send") {
            val otpCode = otpValues.value.joinToString(separator = "")
            println("OTP entered: $otpCode")

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
                    focusedBorderColor = AppColor,
                    unfocusedBorderColor = Color.Gray
                ),
                visualTransformation = VisualTransformation.None
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OtpScreenPreview(modifier: Modifier = Modifier) {
    OtpVerifyScreen(navController = rememberNavController())
}
