package com.example.archivai.presentation

import android.app.Application
import com.example.archivai.data.utils.SharedPrefsHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPrefsHelper.init(this)
    }

}