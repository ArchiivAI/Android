package com.example.archivai.sections.presentation.components

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
import com.example.ui.theme.AppColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionForm() {
    // State for dropdown selection
    var selectedOption by remember { mutableStateOf("Employee") }
    val options = listOf("Employee", "Role")
    var expanded by remember { mutableStateOf(false) }

    // State for text field
    var employeeRoleText by remember { mutableStateOf("") }

    // State for checkboxes
    var viewSection by remember { mutableStateOf(true) }
    var editSection by remember { mutableStateOf(true) }
    var createUploadFolders by remember { mutableStateOf(false) }
    var deleteFolders by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        // Dropdown for Employee/Role
        Text(
            text = "Choose",
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

        // Section Name and Checkboxes
        Text(
            text = "SECTION NAME",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = viewSection,
                onCheckedChange = { viewSection = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = AppColor
                )
            )
            Text(text = "View Section", fontSize = 14.sp, color = Color.Black)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = editSection,
                onCheckedChange = { editSection = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = AppColor
                )
            )
            Text(text = "Edit Section", fontSize = 14.sp, color = Color.Black)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = createUploadFolders,
                onCheckedChange = { createUploadFolders = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = AppColor
                )
            )
            Text(text = "Create/Upload Folders", fontSize = 14.sp, color = Color.Black)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = deleteFolders,
                onCheckedChange = { deleteFolders = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = AppColor
                )
            )
            Text(text = "Delete Folders", fontSize = 14.sp, color = Color.Black)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Apply Button
        Button(
            onClick = { /* Handle apply action */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppColor
            )
        ) {
            Text(text = "Apply", color = Color.White, fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PermissionFormPreview() {
    PermissionForm()
}