package com.example.archivai.presentation.screens.OnBoardingScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.archivai.R
import com.example.archivai.presentation.navigation.Screens
import com.example.archivai.presentation.theme.rubik_regular

@Composable
fun OnBoardingScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.dashboard),
            contentDescription = null,
            Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color(0XFF132863)
                        .copy(0.85F)
                )

        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Spacer(modifier = Modifier.size(148.dp))
            Image(
                painter = painterResource(R.drawable.onb1),
                contentDescription = null, modifier = Modifier.size(200.dp))

            Spacer(modifier = Modifier.size(96.dp))

            Text(
                text = "Streamline your document management \n" +
                        "with AI-powered tools designed to save \n" +
                        "time and reduce effort.",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 18.sp,

                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Normal,
                fontFamily = rubik_regular

            )
            Spacer(modifier = Modifier.size(48.dp))
            Row (

            ){
                Image(painter = painterResource(R.drawable.rectangle_white), contentDescription = null, modifier = Modifier.size(12.dp))
                Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                Image(painter = painterResource(R.drawable.rectangle_black), contentDescription = null, modifier = Modifier.size(12.dp))
                Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                Image(painter = painterResource(R.drawable.rectangle_black), contentDescription = null, modifier = Modifier.size(12.dp))



            }
            Spacer(modifier = Modifier.size(96.dp))
            Button(
                onClick = {  navController.navigate(Screens.Login) },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0XFFDDE5FF))
            ) {
                Text(
                    "Next", color = Color(0XFF132863),
                    fontSize = 16.sp,
                    fontFamily = rubik_regular,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )

            }
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = "Skip", textDecoration = TextDecoration.Underline, fontSize = 14.sp, color = Color.White)


        }


    }


}




@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview(modifier: Modifier = Modifier) {
    OnBoardingScreen(rememberNavController())
}