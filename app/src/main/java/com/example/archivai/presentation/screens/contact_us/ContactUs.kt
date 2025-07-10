package com.example.archivai.presentation.screens.contact_us


import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.archivai.R
import com.example.archivai.presentation.screens.login_screen.composables.ImageContainer
import com.example.archivai.presentation.screens.login_screen.composables.RectangleButton
import com.example.archivai.presentation.screens.login_screen.composables.Spacer10
import com.example.archivai.presentation.screens.login_screen.composables.Spacer24
import com.example.archivai.presentation.screens.login_screen.composables.Spacer32
import com.example.archivai.presentation.screens.login_screen.composables.Spacer50
import com.example.archivai.presentation.screens.login_screen.composables.Spacer64
import com.example.archivai.presentation.screens.login_screen.composables.TopText
import com.example.archivai.presentation.theme.play_fair_font

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactUsScreen(navController: NavController , viewModel: ContactUsViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsState()
    var email by remember { mutableStateOf("")}
    val context = LocalContext.current

    LaunchedEffect(uiState) {
        when (uiState) {
            is UiState.Success -> {
                Toast.makeText(context, (uiState as UiState.Success).message, Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            }
            is UiState.Error -> {
                Toast.makeText(context, (uiState as UiState.Error).error, Toast.LENGTH_SHORT).show()
            }
            else -> Unit
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Spacer32()
        TopText("Contact With ArchivAI")
        Spacer64()
        Spacer10()
        ImageContainer(R.drawable.chatbot_in_smartphone)
        Spacer50()
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
        Spacer24()
        RectangleButton("Send") { viewModel.sendContactEmail(email) }


    }






}
@Preview(showBackground = true)
@Composable
fun ContacrUsScreenPreview(modifier: Modifier = Modifier) {
    //ContactUsScreen(navController = rememberNavController() , viewModel = ContactUsViewModel())

}