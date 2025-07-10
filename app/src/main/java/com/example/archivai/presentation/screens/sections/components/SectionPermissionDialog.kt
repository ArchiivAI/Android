package com.example.archivai.presentation.screens.sections.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.archivai.presentation.screens.sections.SectionsViewModel
import com.example.archivai.presentation.screens.sections.components.SectionPermission.values
import com.example.archivai.presentation.theme.AppColor
import com.example.archivai.presentation.theme.rubik_semibold
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager

sealed class PermissionType {
    object EMPLOYEE : PermissionType()
    object ROLE : PermissionType()
}

data class Entity(
    val id: Int,
    val name: String,
    val type: PermissionType
)

// Enum for permission levels
enum class SectionPermission(val value: Int) {
    VIEW(0),
    EDIT(1),
    CREATE_UPLOAD(2),
    DELETE(3);

    companion object {
        fun fromValue(value: Int): SectionPermission {
            return values().first { it.value == value }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SectionPermissionDialog(
    onDismissRequest: () -> Unit,
    viewModel: SectionsViewModel,
    sectionName: String,
) {
    val state by viewModel.uiState.collectAsState()
    var selectedType by remember { mutableStateOf<PermissionType?>(null) }
    var selectedEntity by remember { mutableStateOf<Entity?>(null) }
    var permissions by remember { mutableStateOf<List<Int>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    // Fetch employees and roles when dialog opens
    LaunchedEffect(Unit) {
        viewModel.getEmployeesAndRoles()
    }

    // Update permissions when entity is selected
    LaunchedEffect(selectedEntity) {
        selectedEntity?.let { entity ->
            isLoading = true
            permissions = when (entity.type) {
                is PermissionType.EMPLOYEE -> {
                    viewModel.getSectionEmployeePermissionsUseCase(
                        entity.id,
                        state.selectedSection?.id ?: 0

                    )
                }

                is PermissionType.ROLE -> {
                    viewModel.getSectionRolePermissionsUseCase(
                        state.selectedSection?.id ?: 0,
                        entity.id
                    )
                }
            }
            isLoading = false
        }
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "$sectionName Permissions",
                    style = MaterialTheme.typography.headlineMedium,
                    color = AppColor,
                    fontFamily = rubik_semibold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Type selection (Employee/Role)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedButton(
                        onClick = {
                            selectedType = PermissionType.EMPLOYEE
                            selectedEntity = null
                            focusManager.clearFocus()
                        },
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = if (selectedType is PermissionType.EMPLOYEE) Color.White else Color.Gray,
                            containerColor = if (selectedType is PermissionType.EMPLOYEE) AppColor else Color.Transparent
                        )
                    ) {
                        Text("Employee")
                    }

                    OutlinedButton(
                        onClick = {
                            selectedType = PermissionType.ROLE
                            selectedEntity = null
                            focusManager.clearFocus()
                        },
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = if (selectedType is PermissionType.ROLE) Color.White else Color.Gray,
                            containerColor = if (selectedType is PermissionType.ROLE) AppColor else Color.Transparent
                        )
                    ) {
                        Text("Role")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Entity selection dropdown
                selectedType?.let { type ->
                    val entities = when (type) {
                        is PermissionType.EMPLOYEE -> state.employees.map {
                            Entity(it.id, it.firstName + " " + it.lastName, PermissionType.EMPLOYEE)
                        }

                        is PermissionType.ROLE -> state.roles.map {
                            Entity(it.id, it.name, PermissionType.ROLE)
                        }
                    }

                    var expanded by remember { mutableStateOf(false) }

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = it }
                    ) {
                        OutlinedTextField(
                            value = selectedEntity?.name ?: "",
                            onValueChange = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor()
                                .focusRequester(focusRequester),
                        readOnly = true,
                        label = {
                            Text(
                                text = when (type) {
                                    is PermissionType.EMPLOYEE -> "Selected Employee"
                                    is PermissionType.ROLE -> "Selected Role"
                                }
                            )
                        },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                            focusedBorderColor = AppColor,
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = AppColor,
                            unfocusedLabelColor = Color.Gray,
                            focusedTrailingIconColor = AppColor,
                            unfocusedTrailingIconColor = Color.Gray,
                            disabledTextColor = Color.Black,
                            focusedTextColor = Color.Black
                        )
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.background(Color.White)
                        ) {
                            entities.forEach { entity ->
                                DropdownMenuItem(
                                    text = { Text(entity.name) },
                                    colors = MenuItemColors(
                                        textColor = AppColor,
                                        disabledTextColor = Color.Black,
                                        leadingIconColor = AppColor,
                                        trailingIconColor = AppColor,
                                        disabledLeadingIconColor = AppColor,
                                        disabledTrailingIconColor = AppColor
                                    ),
                                    onClick = {
                                        selectedEntity = entity
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Permissions checkboxes with hierarchical constraints
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color = AppColor
                    )
                } else if (selectedEntity != null) {
                    Text(
                        text = "SECTION PERMISSIONS",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    val viewChecked = permissions.contains(SectionPermission.VIEW.value)
                    val editChecked = permissions.contains(SectionPermission.EDIT.value)
                    val createUploadChecked =
                        permissions.contains(SectionPermission.CREATE_UPLOAD.value)
                    val deleteChecked = permissions.contains(SectionPermission.DELETE.value)

                    // View permission
                    PermissionCheckboxRow(
                        text = "View Section",
                        checked = viewChecked,
                        onCheckedChange = { checked ->
                            permissions = if (checked) {
                                permissions.toMutableSet().apply {
                                    add(SectionPermission.VIEW.value)
                                }.toList()
                            } else {
                                // If unchecking view, uncheck all higher permissions
                                emptyList()
                            }
                        }
                    )

                    // Edit permission
                    PermissionCheckboxRow(
                        text = "Edit Section",
                        checked = editChecked,
                        enabled = viewChecked,
                        onCheckedChange = { checked ->
                            permissions = if (checked) {
                                permissions.toMutableSet().apply {
                                    add(SectionPermission.VIEW.value)
                                    add(SectionPermission.EDIT.value)
                                }.toList()
                            } else {
                                // If unchecking edit, uncheck all higher permissions
                                permissions.filter { it <= SectionPermission.VIEW.value }
                            }
                        }
                    )

                    // Create/Upload permission
                    PermissionCheckboxRow(
                        text = "Create/Upload Folders",
                        checked = createUploadChecked,
                        enabled = viewChecked && editChecked,
                        onCheckedChange = { checked ->
                            permissions = if (checked) {
                                permissions.toMutableSet().apply {
                                    add(SectionPermission.VIEW.value)
                                    add(SectionPermission.EDIT.value)
                                    add(SectionPermission.CREATE_UPLOAD.value)
                                }.toList()
                            } else {
                                // If unchecking create/upload, uncheck delete
                                permissions.filter { it <= SectionPermission.EDIT.value }
                            }
                        }
                    )

                    // Delete permission
                    PermissionCheckboxRow(
                        text = "Delete Folders",
                        checked = deleteChecked,
                        enabled = viewChecked && editChecked && createUploadChecked,
                        onCheckedChange = { checked ->
                            permissions = if (checked) {
                                permissions.toMutableSet().apply {
                                    add(SectionPermission.VIEW.value)
                                    add(SectionPermission.EDIT.value)
                                    add(SectionPermission.CREATE_UPLOAD.value)
                                    add(SectionPermission.DELETE.value)
                                }.toList()
                            } else {
                                permissions.filter { it != SectionPermission.DELETE.value }
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Apply/Cancel buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = onDismissRequest,
                        modifier = Modifier.padding(end = 8.dp),
                        colors = ButtonColors(
                            containerColor = Color.White,
                            contentColor = AppColor,
                            disabledContainerColor = Color.LightGray,
                            disabledContentColor = Color.Gray
                        )
                    ) {
                        Text("Cancel")
                    }

                    Button(
                        onClick = {
                            selectedEntity?.let { entity ->
                                when (entity.type) {
                                    is PermissionType.EMPLOYEE -> {
                                        viewModel.updateEmployeePermissions(
                                            employeeId = entity.id,
                                            sectionId = state.selectedSection?.id ?: 0,
                                            permissions = permissions
                                        )
                                    }

                                    is PermissionType.ROLE -> {
                                        viewModel.updateRolePermissions(
                                            state.selectedSection?.id ?: 0,
                                            entity.id,
                                            permissions
                                        )
                                    }
                                }
                                onDismissRequest()
                            }
                        },
                        enabled = selectedEntity != null,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppColor,
                            contentColor = Color.White,
                            disabledContainerColor = Color.LightGray,
                            disabledContentColor = Color.Gray


                        )
                    ) {
                        Text("Apply")
                    }
                }
            }
        }
    }
}

@Composable
private fun PermissionCheckboxRow(
    text: String,
    checked: Boolean,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
            colors = CheckboxDefaults.colors(
                checkedColor = AppColor,
                uncheckedColor = if (enabled) Color.Gray else Color.LightGray,
                checkmarkColor = Color.White,
                disabledCheckedColor = Color.White,
                disabledIndeterminateColor = Color.Gray,
                disabledUncheckedColor = Color.LightGray
            )
        )
        Text(
            text = text,
            color = if (enabled) Color.Black else Color.Gray,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}


