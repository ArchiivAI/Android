package com.example.archivai.presentation.auth.ui.composables

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.archivai.presentation.ui.theme.play_fair_font

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordContainer(label : String) {
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = password,
        onValueChange = {password = it},
        label = { Text(text = label , color = Color(0xFF132863), fontFamily = play_fair_font, fontWeight = FontWeight.Normal) },
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