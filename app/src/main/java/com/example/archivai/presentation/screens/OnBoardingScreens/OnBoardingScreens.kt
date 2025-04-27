package com.example.archivai.presentation.screens.OnBoardingScreens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.archivai.R
import com.example.archivai.presentation.navigation.Screens
import kotlinx.coroutines.launch
import com.example.archivai.presentation.theme.rubik_regular
import com.example.archivai.presentation.theme.play_fair_font

@Composable
fun OnBoardingScreens(navController: NavController) {
    val pagerState = rememberPagerState(pageCount = { 3 }) // 3 pages
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        // Background
        Image(
            painter = painterResource(R.drawable.dashboard),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0XFF132863).copy(0.85f))
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { page ->
                when (page) {
                    0 -> OnBoardingPage(
                        imageRes = R.drawable.onb1,
                        text = "Streamline your document management \nwith AI-powered tools designed to save \ntime and reduce effort.",
                        fontFamily = rubik_regular,
                        currentPage = 0
                    )
                    1 -> OnBoardingPage(
                        imageRes = R.drawable.onb2,
                        text = "Automatically organize, classify, and\nsearch your files effortlessly with smart \nfeatures tailored to your needs.",
                        fontFamily = play_fair_font,
                        currentPage = 1
                    )
                    2 -> OnBoardingPage(
                        imageRes = R.drawable.onb3,
                        text = "Securely access your documents anytime,\nanywhere, with advanced permissions\nand seamless navigation",
                        fontFamily = play_fair_font,
                        currentPage = 2
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(3) { index ->
                    val color = if (pagerState.currentPage == index) Color(0xFFCDAD8F) else Color.Gray
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(color, shape = RoundedCornerShape(6.dp))
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (pagerState.currentPage == 2) {
                        navController.navigate(Screens.Login) // Navigate to login after last page
                    } else {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0XFFDDE5FF))
            ) {
                Text(
                    if (pagerState.currentPage == 2) "Get Started" else "Next",
                    color = Color(0XFF132863),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (pagerState.currentPage < 2) {
                Text(
                    "Skip",
                    textDecoration = TextDecoration.Underline,
                    color = Color.White,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                        .clickable {
                            navController.navigate(Screens.Login)
                        }
                )
            } else {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun OnBoardingPage(
    imageRes: Int,
    text: String,
    fontFamily: androidx.compose.ui.text.font.FontFamily,
    currentPage: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = text,
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = fontFamily,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Normal
        )
    }
}
