package com.example.archivai.presentation.screens.forget_password.forget_password

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.archivai.R
import com.example.archivai.presentation.screens.login_screen.composables.EmailContainer
import com.example.archivai.presentation.screens.login_screen.composables.ImageContainer
import com.example.archivai.presentation.screens.login_screen.composables.RectangleButton
import com.example.archivai.presentation.screens.login_screen.composables.Spacer10
import com.example.archivai.presentation.screens.login_screen.composables.Spacer16
import com.example.archivai.presentation.screens.login_screen.composables.Spacer32
import com.example.archivai.presentation.screens.login_screen.composables.Spacer64
import com.example.archivai.presentation.screens.login_screen.composables.TopText

import com.example.archivai.presentation.theme.play_fair_font

@Composable
fun ForgetPasswordScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        TopText("Find Your Account")
        Spacer64()
        ImageContainer(R.drawable.chatgpt_robot_holding_loupe)
        Spacer32()
        Spacer10()
        EmailContainer()
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
        RectangleButton("Send",{})







    }





    
}
@Preview(showBackground = true)
@Composable
fun ForgetPasswordScreenPreview(modifier: Modifier = Modifier) {
    ForgetPasswordScreen(navController = rememberNavController())

}