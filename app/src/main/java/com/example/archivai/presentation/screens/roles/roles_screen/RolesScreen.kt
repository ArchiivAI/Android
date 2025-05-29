package com.example.archivai.presentation.screens.roles.roles_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.archivai.R
import com.example.archivai.presentation.navigation.Screens
import com.example.archivai.presentation.screens.roles.components.DeleteRoleDialog
import com.example.archivai.presentation.screens.roles.components.RenameRoleDialog
import com.example.archivai.presentation.screens.roles.components.RoleCard
import com.example.archivai.presentation.screens.roles.components.RolesBottomSheet
import com.example.archivai.presentation.screens.sections.components.DeleteSectionDialog
import com.example.archivai.presentation.theme.AppColor
import com.example.archivai.presentation.theme.rubik_semibold

@Composable
fun RolesScreen(navController: NavController, viewModel: RolesViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()
    var newRoleName by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 24.dp, horizontal = 24.dp)


        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    .padding(6.dp)
            ) {
                Text(
                    text = "Roles",
                    fontFamily = rubik_semibold,
                    fontSize = 20.sp,
                    color = AppColor
                )
                Spacer(modifier = Modifier.weight(1F))
                Icon(
                    painterResource(R.drawable.add_icon),
                    contentDescription = "add icon",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            navController.navigate(Screens.AddNewRole)
                        },
                    tint = AppColor
                )


            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
            ) {
                when {
                    state.isLoading -> {
                        // Loading state
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = AppColor
                        )
                    }

                    state.error != null -> {
                        // Error state
                        Column(
                            modifier = Modifier.align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Error loading roles",
                                fontSize = 16.sp,
                                color = Color.Red,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = state.error!!,
                                fontSize = 14.sp,
                                color = Color.Gray,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                    }

                    state.roles.isEmpty() -> {
                        // Empty state
                        Text(
                            text = "No roles available",
                            fontSize = 16.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    else -> {
                        LazyColumn(
                            modifier = Modifier.padding(vertical = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            contentPadding = PaddingValues(bottom = 30.dp)
                        ) {
                            items(state.roles) { role ->
                                RoleCard(role.id, role.name, 
                                    onMoreOptionsClick =
                                        {
                                            viewModel.selectedRole(role)
                                            viewModel.showSettingsBottomSheet()}
                                    )
                            }


                        }
                    }


                }
                
            }
            if (state.showSettingsBottomSheet){
                RolesBottomSheet(
                    onDismiss = {viewModel.hideSettingsBottomSheet()},
                    onRename = {viewModel.hideSettingsBottomSheet()
                               viewModel.showRoleRenameDialog() },
                    onDelete = {viewModel.showDeleteRoleDialog()}
                )
            }
            if(state.showRenameRoleDialog){
                RenameRoleDialog(
                    initialName = state.selectedRole!!.name,
                    newRoleName = newRoleName,
                    onRoleNameChange = { newRoleName = it },
                    onDismiss = {
                        viewModel.hideRoleRenameDialog()
                        newRoleName = ""
                    },
                    onConfirm = {
                        viewModel.renameRole(state.selectedRole!!.id, newRoleName)
                        Log.d("screen", newRoleName)
                        newRoleName = ""
                        navController.navigate(Screens.Roles)
                    }

                )
            }
            if (state.showDeleteRoleDialog) {
                viewModel.hideSettingsBottomSheet()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                ) {
                    DeleteRoleDialog(
                        onDismiss = { viewModel.hideDeleteRoleDialog() },
                        onConfirm = {viewModel.deleteRole(state.selectedRole!!.id)}
                    )
                }

            }
            
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RolesScreenPreview() {
    RolesScreen(rememberNavController())
}