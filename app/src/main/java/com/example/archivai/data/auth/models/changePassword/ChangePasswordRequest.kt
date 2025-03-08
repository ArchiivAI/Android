package com.example.archivai.data.auth.models.changePassword

data class ChangePasswordRequest(
    val email : String,
    val otp : String,
    val newPassword : String

)
