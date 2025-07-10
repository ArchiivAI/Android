package com.example.archivai.roles.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.archivai.R
import com.example.archivai.presentation.screens.employees.add_new_employee.AddNewEmployeeUiEvent
import com.example.archivai.presentation.screens.employees.add_new_employee.AddNewEmployeeViewModel
import com.example.archivai.presentation.screens.employees.components.RoleChip
import com.example.archivai.presentation.screens.login_screen.composables.Spacer16
import com.example.archivai.presentation.screens.login_screen.composables.Spacer24
import com.example.archivai.presentation.theme.AppColor
import com.example.archivai.presentation.theme.rubik_medium
import com.example.archivai.presentation.theme.rubik_semibold
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddNewEmployeeScreen(
    navController: NavController,
    viewModel: AddNewEmployeeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is AddNewEmployeeUiEvent.ShowToast ->
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()

                is AddNewEmployeeUiEvent.NavigateBack -> navController.popBackStack()
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 48.dp, start = 24.dp, end = 24.dp)
                .padding(bottom = 72.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
            ) {
                Icon(
                    painterResource(R.drawable.arrow_icon),
                    contentDescription = "back icon",
                    modifier = Modifier
                        .clickable { navController.popBackStack() }
                        .align(Alignment.CenterVertically)
                        .padding(6.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Add New Employee",
                    fontFamily = rubik_semibold,
                    fontSize = 20.sp,
                    color = AppColor,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            Spacer24()

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Column {
                    Text(
                        "Employee First Name",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = rubik_medium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    buildHintTextField(
                        state.firstName,
                        "Enter first name",
                        viewModel::updateFirstName
                    )
                }

                Column {
                    Text(
                        "Employee Last Name",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = rubik_medium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    buildHintTextField(state.lastName, "Enter last name", viewModel::updateLastName)
                }

                Column {
                    Text(
                        "Employee Email",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = rubik_medium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    buildHintTextField(state.email, "Enter email", viewModel::updateEmail)
                }

                // Role Selection
                Column {
                    Text(
                        "Employee Role",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = rubik_medium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    if (state.selectedRoles.isNotEmpty()) {
                        FlowRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            state.selectedRoles.forEach { role ->
                                RoleChip(role = role, onRemove = { viewModel.removeRole(role) })
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    Box(modifier = Modifier.fillMaxWidth()) {
                        Column {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { viewModel.toggleDropdown() }
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
                                        text = if (state.availableRoles.isEmpty()) "No roles available" else "Select roles",
                                        fontSize = 16.sp,
                                        color = if (state.availableRoles.isEmpty()) Color.Gray else Color.Black
                                    )
                                    Icon(
                                        imageVector = Icons.Default.ArrowDropDown,
                                        contentDescription = "Dropdown",
                                        tint = Color.Black
                                    )
                                }
                            }

                            DropdownMenu(
                                expanded = state.isDropdownExpanded,
                                onDismissRequest = { viewModel.toggleDropdown() },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White)
                            ) {
                                state.availableRoles.forEach { role ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(role.name, color = Color.Black, fontSize = 16.sp)
                                        },
                                        onClick = {
                                            viewModel.selectRole(role)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer16()

                Button(
                    onClick = { viewModel.addEmployee() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColor,
                        contentColor = Color.White,
                        disabledContainerColor = Color(0xFFE0E0E0),
                        disabledContentColor = Color.DarkGray
                    ),
                    enabled = state.firstName.isNotBlank() &&
                            state.lastName.isNotBlank() &&
                            state.email.isNotBlank() &&
                            state.selectedRoles.isNotEmpty()
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Text(
                            text = "Add Employee",
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

@Composable
fun buildHintTextField(
    value: String,
    hint: String,
    onValueChange: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .border(1.dp, AppColor, RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 14.dp)
    ) {
        if (value.isEmpty()) {
            Text(text = hint, color = Color.Gray, fontSize = 16.sp)
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
            singleLine = true
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AddNewEmployeeScreenPreview() {
    AddNewEmployeeScreen(rememberNavController())
}
