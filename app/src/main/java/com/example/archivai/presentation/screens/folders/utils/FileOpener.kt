package com.example.archivai.presentation.screens.folders.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.core.net.toUri

class FileOpener(private val context: Context) {

    fun openFile(fileUrl: String, mimeType: String?) {
        try {
            val uri = fileUrl.toUri()
            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(uri, mimeType ?: getMimeType(uri))
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            context.startActivity(Intent.createChooser(intent, "Open with"))
        } catch (e: Exception) {
            // Handle error (no app available to open this file)
            Toast.makeText(context, "No app found to open this file", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getMimeType(uri: Uri): String {
        return when (val extension = MimeTypeMap.getFileExtensionFromUrl(uri.toString())) {
            null -> "*/*"
            else -> MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension) ?: "*/*"
        }
    }
}