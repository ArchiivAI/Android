package com.example.archivai.presentation.screens.folders.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.example.archivai.presentation.screens.folders.FolderViewModel
import com.example.archivai.presentation.screens.folders.utils.toBitmap
import com.example.archivai.presentation.theme.AppColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadFileConfirmationBottomSheet(
    viewModel: FolderViewModel,
    onDismissRequest: () -> Unit,
    isPhotoMode: Boolean,
    folderId: Int?
) {
    val state by viewModel.uiState.collectAsState()

    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() },
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        containerColor = Color(0xFFF6F8FF),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isPhotoMode) {
                state.capturedImage?.let { imageFile ->
                    Image(
                        bitmap = imageFile.toBitmap().asImageBitmap(),
                        contentDescription = "Captured photo",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(3f / 4f)
                            .clip(RoundedCornerShape(16.dp))
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    if (state.isUploading) {
                        CircularProgressIndicator(color = AppColor)
                    } else {
                        Button(
                            onClick = {
                                Log.d("UploadFileConfirmation", "Uploading photo to folder: $folderId")
                                viewModel.uploadFiles(folderId)
                                viewModel.showUploadTrackingSheet()
                                onDismissRequest()
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = AppColor, contentColor = Color.White),
                            enabled = !state.isUploading
                        ) {
                            Text("Upload Photo")
                        }
                    }
                }
            } else {
                if (state.selectedFiles.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .heightIn(max = 200.dp)
                            .fillMaxWidth()
                    ) {
                        items(state.selectedFiles) { file ->
                            FileChip(
                                file = file,
                                progress = state.uploadProgress[file],
                                onRemove = { viewModel.removeSelectedFile(file) }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    if (state.isUploading) {
                        CircularProgressIndicator(color = AppColor)
                    } else {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
                        ) {
                            OutlinedButton(
                                onClick = {
                                    viewModel.clearSelectedFiles()
                                    onDismissRequest()
                                },
                                enabled = !state.isUploading,
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor= Color.Black)
                            ) {
                                Text("Cancel")
                            }

                            Button(
                                onClick = {
                                    Log.d("UploadFileConfirmation", "Uploading files to folder: $folderId")
                                    viewModel.uploadFiles(folderId)
                                    viewModel.showUploadTrackingSheet()
                                    onDismissRequest()
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = AppColor, contentColor = Color.White),
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
}
