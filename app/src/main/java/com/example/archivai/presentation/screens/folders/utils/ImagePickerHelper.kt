package com.example.archivai.presentation.screens.folders.utils



import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun rememberCameraCapture(
    onImageCaptured: (File) -> Unit,
    onError: (String) -> Unit = {}
): () -> Unit {
    val context = LocalContext.current
    var currentPhotoFile by remember { mutableStateOf<File?>(null) }
    var showPermissionDialog by remember { mutableStateOf(false) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                currentPhotoFile?.let(onImageCaptured)
            } else {
                onError("Camera capture failed")
            }
        }
    )

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            try {
                currentPhotoFile = createImageFile(context)
                val photoUri = FileProvider.getUriForFile(
                    context,
                    "${context.packageName}.provider",
                    currentPhotoFile!!
                )
                cameraLauncher.launch(photoUri)
            } catch (e: Exception) {
                onError("Camera error: ${e.localizedMessage}")
            }
        } else {
            showPermissionDialog = true
        }
    }

    if (showPermissionDialog) {
        AlertDialog(
            onDismissRequest = { showPermissionDialog = false },
            title = { Text("Camera Permission Required") },
            text = { Text("Please enable camera permissions in app settings") },
            confirmButton = {
                Button(onClick = {
                    showPermissionDialog = false
                    context.startActivity(
                        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                            data = Uri.fromParts("package", context.packageName, null)
                        }
                    )
                }) {
                    Text("Open Settings")
                }
            },
            dismissButton = {
                TextButton(onClick = { showPermissionDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    return {
        permissionLauncher.launch(Manifest.permission.CAMERA)
    }
}

private fun createImageFile(context: Context): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(
        "JPEG_${timeStamp}_",
        ".jpg",
        storageDir
    )
}