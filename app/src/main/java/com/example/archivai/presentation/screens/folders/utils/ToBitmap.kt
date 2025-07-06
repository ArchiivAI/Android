package com.example.archivai.presentation.screens.folders.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File

fun File.toBitmap(): Bitmap {
    return BitmapFactory.decodeFile(this.absolutePath)
}