package com.example.archivai.presentation.auth.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.archivai.R
import com.example.archivai.presentation.ui.theme.play_fair_font

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = "Welcome To ArchivAI", fontSize = 32.sp , color = Color(0XFF132863) , fontFamily = play_fair_font , fontWeight = FontWeight.Normal)
        Spacer(modifier = Modifier.size(32.dp))
        Image(painter = painterResource(R.drawable.archive_ai_logo), contentDescription = null
        , modifier = Modifier.size(240.dp))

OutlinedTextField(
    value = email,
    onValueChange = {email = it},
    label = { Text(text = "Email" , color = Color(0xFF132863), fontFamily = play_fair_font, fontWeight = FontWeight.Normal) },
    leadingIcon = { Icon(imageVector = Icons.Default.Email,contentDescription = null, tint = Color(0XFF132863)) },
    modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp),colors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = Color(0xFF132863),
        unfocusedBorderColor = Color(0xFF132863))


)
        Spacer(modifier = Modifier.size(16.dp))
OutlinedTextField(
    value = password,
    onValueChange = {password = it},
    label = { Text(text = "Password" , color = Color(0xFF132863), fontFamily = play_fair_font, fontWeight = FontWeight.Normal) },
    leadingIcon = { Icon(imageVector = Icons.Default.Lock,contentDescription = null, tint = Color(0XFF132863)) },
    modifier = Modifier.fillMaxWidth().padding(start=16.dp, end = 16.dp),colors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = Color(0xFF132863),
        unfocusedBorderColor = Color(0xFF132863)),
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
        Spacer(modifier = Modifier.size(10.dp))
        Row(modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
            ){
            Row(verticalAlignment = Alignment.CenterVertically){
                Checkbox(checked = rememberMe , onCheckedChange = {rememberMe = it}, colors = CheckboxDefaults.colors(
                    checkedColor = Color(0XFF132863), // Checkbox fill color when checked
                    uncheckedColor = Color(0XFF132863), // Background color when unchecked
                    checkmarkColor = Color(0XFF132863), // Checkmark color
                    disabledUncheckedColor = Color(0XFF132863)
                ))
                Text(text = "Remember me" , fontFamily = play_fair_font , fontSize = 16.sp, color = Color.Gray)


            }

                TextButton(onClick = {}) {
                    Text(text = "Forgot password?", fontFamily = play_fair_font , fontSize = 16.sp , style = TextStyle(textDecoration = TextDecoration.Underline), color = Color(0XFF132863))
                }


        }
        Spacer(modifier = Modifier.size(32.dp))
        Button(onClick = {},
            modifier = Modifier.fillMaxWidth().height(48.dp).padding(start=24.dp,end =24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF132863))
        ) {
            Text(text = "Log in",fontSize= 18.sp, color = Color.White)

        }
        Spacer(modifier = Modifier.size(24.dp))
        Row (verticalAlignment = Alignment.CenterVertically){
            Text(text = "Don't have an account?", fontSize = 16.sp, fontFamily = play_fair_font , fontWeight = FontWeight.Normal , color = Color.DarkGray )
            TextButton(onClick = {}) {
                Text(text = "Contact us" , fontSize = 16.sp, textDecoration = TextDecoration.Underline , color = Color(0XFF132863))


            }


        }














    }






}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(modifier: Modifier = Modifier) {
    LoginScreen()

}