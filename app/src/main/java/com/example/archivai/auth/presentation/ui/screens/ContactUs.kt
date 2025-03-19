package com.example.archivai.auth.presentation.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.archivai.R
import com.example.archivai.presentation.auth.ui.composables.EmailContainer
import com.example.archivai.presentation.auth.ui.composables.ImageContainer
import com.example.archivai.presentation.auth.ui.composables.RectangleButton
import com.example.archivai.presentation.auth.ui.composables.Spacer10
import com.example.archivai.presentation.auth.ui.composables.Spacer16
import com.example.archivai.presentation.auth.ui.composables.Spacer32
import com.example.archivai.presentation.auth.ui.composables.Spacer64
import com.example.archivai.presentation.auth.ui.composables.TopText

@Composable
fun ContactUsScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        TopText("Contact With ArchivAI")
        Spacer64()
        ImageContainer(R.drawable.chatgpt_robot_holding_loupe)
        Spacer32()
        Spacer10()
        EmailContainer()
        Spacer16()

        Spacer64()
        RectangleButton("Send")







    }






}
@Preview(showBackground = true)
@Composable
fun ContacrUsScreenPreview(modifier: Modifier = Modifier) {
    ContactUsScreen()

}