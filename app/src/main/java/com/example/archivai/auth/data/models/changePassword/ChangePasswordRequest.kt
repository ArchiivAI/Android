package com.example.archivai.auth.data.models.changePassword

data class ChangePasswordRequest(
    val email : String,
    val otp : String,
    val newPassword : String

)
