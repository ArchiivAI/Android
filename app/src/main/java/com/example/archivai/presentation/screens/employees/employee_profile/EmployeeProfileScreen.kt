package com.example.archivai.presentation.screens.employees.employee_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.archivai.R
import com.example.archivai.presentation.navigation.Screens
import com.example.archivai.presentation.screens.employees.components.PermissionCard
import com.example.archivai.presentation.screens.login_screen.composables.Spacer16
import com.example.archivai.presentation.screens.login_screen.composables.Spacer24
import com.example.archivai.presentation.theme.AppColor
import com.example.archivai.presentation.theme.rubik_medium
import com.example.archivai.presentation.theme.rubik_semibold

@Composable
fun EmployeeProfileScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {

            // Top Bar (always visible)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(horizontal = 24.dp, vertical = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_icon),
                    contentDescription = "back icon",
                    modifier = Modifier
                        .clickable { navController.popBackStack() }
                        .padding(6.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Ahmed's Profile",
                    fontFamily = rubik_semibold,
                    fontSize = 20.sp,
                    color = AppColor
                )
            }

            // Content (scrollable)
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 24.dp, end = 24.dp, bottom = 72.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item { Spacer16() }

                item {
                    Column {
                        Text(
                            text = "Employee First Name",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = rubik_medium,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        BasicTextField(
                            value = "Ahmed",
                            onValueChange = { /* Handle input change */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White, shape = RoundedCornerShape(8.dp))
                                .border(1.dp, AppColor, RoundedCornerShape(8.dp))
                                .padding(12.dp),
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                color = Color.Black
                            ),
                            singleLine = true
                        )
                    }
                }

                item {
                    Column {
                        Text(
                            text = "Employee last Name",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = rubik_medium,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        BasicTextField(
                            value = "Ali",
                            onValueChange = { /* Handle input change */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White, shape = RoundedCornerShape(8.dp))
                                .border(1.dp, AppColor, RoundedCornerShape(8.dp))
                                .padding(12.dp),
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                color = Color.Black
                            ),
                            singleLine = true
                        )
                    }
                }

                item {
                    Column {
                        Text(
                            text = "Employee Email",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = rubik_medium,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        BasicTextField(
                            value = "ahmedali11@archivai.net",
                            onValueChange = { /* Handle input change */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White, shape = RoundedCornerShape(8.dp))
                                .border(1.dp, AppColor, RoundedCornerShape(8.dp))
                                .padding(12.dp),
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                color = Color.Black
                            ),
                            singleLine = true
                        )
                    }
                }

                item {
                    Column {
                        Text(
                            text = "Employee Role",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = rubik_medium,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White, shape = RoundedCornerShape(8.dp))
                                .border(1.dp, AppColor, RoundedCornerShape(8.dp))
                                .padding(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "HR specialist",
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = "Dropdown",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                }

                item { Spacer16() }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Permissions",
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
                }

                item { Spacer(modifier = Modifier.height(6.dp)) }

                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        PermissionCard(folderName = "Folder Name", onDeleteClick = { /* Handle delete */ })
                        PermissionCard(folderName = "Folder Name", onDeleteClick = { /* Handle delete */ })
                        PermissionCard(folderName = "Section Name", onDeleteClick = { /* Handle delete */ })
                    }
                }

                item {
                    Button(
                        onClick = { /* Handle button click */ },
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
                            text = "Update",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = rubik_medium
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun EmployeeProfileScreenPreview() {
    EmployeeProfileScreen(rememberNavController())
}
