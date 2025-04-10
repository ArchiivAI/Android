package com.example.archivai.auth.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.archivai.R
import com.example.archivai.auth.presentation.ui.composables.ImageContainer
import com.example.archivai.auth.presentation.ui.composables.Spacer50
import com.example.archivai.auth.presentation.ui.composables.TopText

import com.example.ui.theme.play_fair_font

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPasswordScreen() {

    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        TopText("Create New Password")
        Spacer50()
        ImageContainer(R.drawable.small_chatbot_with_locked_padlock)
        Spacer50()
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text(text = "New Password" , color = Color(0xFF132863), fontFamily = play_fair_font, fontWeight = FontWeight.Normal) },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock,contentDescription = null, tint = Color(0XFF132863)) },
            modifier = Modifier.fillMaxWidth().padding(start=16.dp, end = 16.dp),colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF132863),
                unfocusedBorderColor = Color(0xFF132863)
            ),
            trailingIcon = {
                IconButton(onClick = {passwordVisible = !passwordVisible}) {
                    Icon(
                        imageVector = if(passwordVisible)
                            Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                        contentDescription = null)
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        )
OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text(text = "Confirm Password" , color = Color(0xFF132863), fontFamily = play_fair_font, fontWeight = FontWeight.Normal) },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock,contentDescription = null, tint = Color(0XFF132863)) },
            modifier = Modifier.fillMaxWidth().padding(start=16.dp, end = 16.dp),colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF132863),
                unfocusedBorderColor = Color(0xFF132863)
            ),
            trailingIcon = {
                IconButton(onClick = {passwordVisible = !passwordVisible}) {
                    Icon(
                        imageVector = if(passwordVisible)
                            Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                        contentDescription = null)
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        )





    }

}

@Preview(showBackground = true)
@Composable
fun NewPasswordScreenPreview(modifier: Modifier = Modifier) {

}