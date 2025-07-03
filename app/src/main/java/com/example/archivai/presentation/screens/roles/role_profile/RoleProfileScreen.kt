package com.example.archivai.presentation.screens.roles.role_profile

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.archivai.R
import com.example.archivai.presentation.navigation.Screens
import com.example.archivai.presentation.screens.employees.components.PermissionCard
import com.example.archivai.presentation.theme.AppColor
import com.example.archivai.presentation.theme.rubik_medium
import com.example.archivai.presentation.theme.rubik_semibold

@Composable
fun RoleProfileScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_icon),
                    contentDescription = "Back",
                    modifier = Modifier
                        .clickable { navController.popBackStack() }
                        .padding(6.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "HR Specialist",
                    fontFamily = rubik_semibold,
                    fontSize = 20.sp,
                    color = AppColor
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Role Info Fields
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Role Name Field
                Column {
                    Text(
                        text = "Role Name",
                        fontSize = 14.sp,
                        fontFamily = rubik_medium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, AppColor, shape = RoundedCornerShape(8.dp))
                            .padding(horizontal = 12.dp, vertical = 16.dp)
                    ) {
                        BasicTextField(
                            value = "HR Specialist",
                            onValueChange = {},
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                color = Color.Black
                            ),
                            singleLine = true
                        )
                    }
                }

                // Role ID Field
                Column {
                    Text(
                        text = "Role ID",
                        fontSize = 14.sp,
                        fontFamily = rubik_medium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, AppColor, shape = RoundedCornerShape(8.dp))
                            .padding(horizontal = 12.dp, vertical = 16.dp)
                    ) {
                        BasicTextField(
                            value = "20227894",
                            onValueChange = {},
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                color = Color.Black
                            ),
                            singleLine = true
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Manage Permissions Title + Add Icon
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Manage Role’s Permissions",
                    fontFamily = rubik_semibold,
                    fontSize = 20.sp,
                    color = AppColor
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.add_icon),
                    contentDescription = "Add Permission",
                    tint = AppColor,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            navController.navigate(Screens.AddNewEmployee)
                        }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Permission Cards List
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PermissionCard(folderName = "Folder Name", onDeleteClick = { /* Handle delete */ })
                PermissionCard(folderName = "Folder Name", onDeleteClick = { /* Handle delete */ })
                PermissionCard(folderName = "Section Name", onDeleteClick = { /* Handle delete */ })
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Save Button
            Button(
                onClick = { /* Save action */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColor,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Save",
                    fontSize = 16.sp,
                    fontFamily = rubik_medium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RoleProfileScreenPreview() {
    RoleProfileScreen(navController = rememberNavController())
}
