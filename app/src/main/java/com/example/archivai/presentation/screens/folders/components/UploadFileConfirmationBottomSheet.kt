package com.example.archivai.presentation.screens.folders.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.archivai.presentation.screens.folders.FolderViewModel
import com.example.archivai.presentation.screens.folders.utils.toBitmap
import com.example.archivai.presentation.theme.AppColor

@Composable
fun UploadFileConfirmationBottomSheet(
    viewModel: FolderViewModel,
    onDismissRequest: () -> Unit,
    isPhotoMode: Boolean,
    folderId: Int?
) {
    val context = LocalContext.current
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isPhotoMode) {
            state.capturedImage?.let { imageFile ->
                Image(
                    bitmap = imageFile.toBitmap().asImageBitmap(),
                    contentDescription = "Captured photo",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (state.isUploading) {
                    CircularProgressIndicator(color = AppColor)
                } else {
                    Button(
                        onClick = {
                            Log.d("UploadFileConfirmation", "Uploading photo to folder: $folderId")
                            viewModel.uploadFiles(folderId)
                            onDismissRequest()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = AppColor),
                        enabled = !state.isUploading
                    ) {
                        Text("Upload Photo")
                    }
                }
            }
        } else {
            if (state.selectedFiles.isNotEmpty()) {
                LazyColumn(modifier = Modifier.heightIn(max = 200.dp)) {
                    items(state.selectedFiles) { file ->
                        FileChip(
                            file = file,
                            onRemove = { viewModel.removeSelectedFile(file) }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (state.isUploading) {
                    CircularProgressIndicator()
                } else {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        OutlinedButton(
                            onClick = {
                                viewModel.clearSelectedFiles()
                                onDismissRequest()
                            },
                            enabled = !state.isUploading
                        ) {
                            Text("Cancel")
                        }

                        Button(
                            onClick = {
                                Log.d("UploadFileConfirmation", "Uploading files to folder: $folderId")
                                viewModel.uploadFiles(folderId)
                                onDismissRequest()
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = AppColor),
                            enabled = !state.isUploading
                        ) {
                            Text("Upload Files")
                        }
                    }
                }
            }
        }
    }
}