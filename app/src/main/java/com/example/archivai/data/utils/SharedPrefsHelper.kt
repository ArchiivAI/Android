package com.example.archivai.data.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import androidx.core.content.edit

object SharedPrefsHelper{
    private const val PREFS_NAME = "MyAppPrefs"
    private const val KEY_IS_FIRST_TIME = "is_first_time"
    private const val KEY_IS_LOGGED_IN = "is_logged_in"
    internal const val KEY_TOKEN = "auth_token"
    private var mAppContext: Context? = null
    fun init(appContext: Context?) {
        mAppContext = appContext
    }

    private fun getPrefs(): SharedPreferences {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val context = mAppContext!!

        return try {
            EncryptedSharedPreferences.create(
                PREFS_NAME,
                masterKeyAlias,
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } catch (e: Exception) {

            context.deleteSharedPreferences(PREFS_NAME)


            EncryptedSharedPreferences.create(
                PREFS_NAME,
                masterKeyAlias,
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        }
    }


    fun isFirstTime(): Boolean {
        return getPrefs().getBoolean(KEY_IS_FIRST_TIME, true)
    }

    fun setFirstTimeCompleted() {
        getPrefs().edit() {
            putBoolean(KEY_IS_FIRST_TIME, false)
        }
    }

    // Login state
    fun saveLoginState( token: String) {
        getPrefs().edit() {
            putBoolean(KEY_IS_LOGGED_IN, true)
                .putString(KEY_TOKEN, token)
        }
    }

    fun clearLoginState() {
        getPrefs().edit() {
            remove(KEY_IS_LOGGED_IN)
                .remove(KEY_TOKEN)
        }
    }
    fun isLoggedIn(): Boolean {
        return getPrefs().getBoolean(KEY_IS_LOGGED_IN, false)
    }


    fun getToken(): String? {
        return getPrefs().getString(KEY_TOKEN, null)
    }
}