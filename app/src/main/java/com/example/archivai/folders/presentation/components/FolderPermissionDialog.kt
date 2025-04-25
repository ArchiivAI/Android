package com.example.archivai.folders.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.AppColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FolderPermissionDialog(
    onDismissRequest: () -> Unit,
    onApply: () -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        // State for dropdown selection
        var selectedOption by remember { mutableStateOf("EMPLOYEE") }
        val options = listOf("EMPLOYEE", "ROLE")
        var expanded by remember { mutableStateOf(false) }

        // State for text field
        var employeeRoleText by remember { mutableStateOf("") }

        // State for folder checkboxes
        var viewFolder by remember { mutableStateOf(false) }
        var editFolder by remember { mutableStateOf(false) }
        var deleteFolder by remember { mutableStateOf(false) }
        var createUploadSubFolders by remember { mutableStateOf(false) }
        var deleteSubFolders by remember { mutableStateOf(false) }
        var uploadFiles by remember { mutableStateOf(false) }

        // State for files checkboxes
        var viewFiles by remember { mutableStateOf(true) }
        var deleteFiles by remember { mutableStateOf(true) }
        var editFiles by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            // Dropdown for Employee/Role
            Text(
                text = "CHOOSE",
                fontSize = 14.sp,
                color = AppColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = selectedOption,
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF1E88E5),
                        unfocusedBorderColor = Color.Gray
                    )
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                selectedOption = option
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Text field for Enter Employee/Role
            Text(
                text = "ENTER EMPLOYEE / ROLE",
                fontSize = 12.sp,
                color = AppColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = employeeRoleText,
                onValueChange = { employeeRoleText = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Enter employee or role") },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                    focusedBorderColor = AppColor,
                    unfocusedBorderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Folder Name and Checkboxes
            Text(
                text = "FOLDER NAME",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = viewFolder,
                    onCheckedChange = { viewFolder = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = AppColor
                    )
                )
                Text(text = "View Folder", fontSize = 14.sp, color = Color.Black)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = editFolder,
                    onCheckedChange = { editFolder = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = AppColor
                    )
                )
                Text(text = "Edit Folder", fontSize = 14.sp, color = Color.Black)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = deleteFolder,
                    onCheckedChange = { deleteFolder = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = AppColor
                    )
                )
                Text(text = "Delete Folder", fontSize = 14.sp, color = Color.Black)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = createUploadSubFolders,
                    onCheckedChange = { createUploadSubFolders = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = AppColor
                    )
                )
                Text(text = "Create /Upload Sub Folders", fontSize = 14.sp, color = Color.Black)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = deleteSubFolders,
                    onCheckedChange = { deleteSubFolders = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = AppColor
                    )
                )
                Text(text = "Delete Sub Folders", fontSize = 14.sp, color = Color.Black)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = uploadFiles,
                    onCheckedChange = { uploadFiles = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = AppColor
                    )
                )
                Text(text = "Upload Files", fontSize = 14.sp, color = Color.Black)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Files Section and Checkboxes
            Text(
                text = "FILES",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = viewFiles,
                    onCheckedChange = { viewFiles = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = AppColor
                    )
                )
                Text(text = "View Files", fontSize = 14.sp, color = Color.Black)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = deleteFiles,
                    onCheckedChange = { deleteFiles = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = AppColor
                    )
                )
                Text(text = "Delete Files", fontSize = 14.sp, color = Color.Black)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = editFiles,
                    onCheckedChange = { editFiles = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = AppColor
                    )
                )
                Text(text = "Edit Files", fontSize = 14.sp, color = Color.Black)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Buttons Row (Cancel and Apply)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = { onDismissRequest() },
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text(
                        text = "Cancel",
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }
                Button(
                    onClick = { onApply() },
                    modifier = Modifier,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColor
                    )
                ) {
                    Text(
                        text = "Apply",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FolderPermissionDialogPreview() {
    FolderPermissionDialog(
        onDismissRequest = {},
        onApply = {}
    )
}