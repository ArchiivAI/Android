package com.example.archivai.presentation.screens.folders.utils


import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.webkit.MimeTypeMap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.io.File
import java.io.FileOutputStream

@Composable
fun rememberFilePicker(
    onFilesSelected: (List<File>) -> Unit
): () -> Unit {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments(),
        onResult = { uris ->
            val files = uris.mapNotNull { uri ->
                createTempFileWithExtension(context, uri)
            }
            onFilesSelected(files)
        }
    )

    return {
        launcher.launch(arrayOf("*/*"))
    }
}

private fun createTempFileWithExtension(context: Context, uri: Uri): File? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri) ?: return null
        val originalName = getFileName(context, uri)
        val extension = MimeTypeMap.getFileExtensionFromUrl(originalName)?.let { ".$it" } ?: ""

        // Create temp file with proper extension
        val file = File.createTempFile(
            "temp_${System.currentTimeMillis()}_",
            extension,
            context.cacheDir
        )

        FileOutputStream(file).use { output ->
            inputStream.copyTo(output)
        }
        file
    } catch (e: Exception) {
        null
    }
}

@SuppressLint("Range")
private fun getFileName(context: Context, uri: Uri): String {
    var result = ""
    val cursor = context.contentResolver.query(uri, null, null, null, null)
    cursor?.use {
        if (it.moveToFirst()) {
            result = it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
        }
    }
    return result
}
