package com.example.archivai.auth.presentation.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.theme.play_fair_font

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailContainer(modifier: Modifier = Modifier) {


OutlinedTextField(
value = "",
onValueChange = {},
label = { Text(text = "Email" , color = Color(0xFF132863), fontFamily = play_fair_font, fontWeight = FontWeight.Normal) },
leadingIcon = { Icon(imageVector = Icons.Default.Email,contentDescription = null, tint = Color(0XFF132863)) },
modifier = Modifier
    .fillMaxWidth()
    .padding(start = 16.dp, end = 16.dp),colors = TextFieldDefaults.outlinedTextFieldColors(
focusedBorderColor = Color(0xFF132863),
unfocusedBorderColor = Color(0xFF132863))


)}

@Preview(showBackground = true)
@Composable
fun EmailContainerPreview() {
    EmailContainer()

}
