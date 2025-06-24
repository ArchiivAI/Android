package com.example.archivai.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screens {

    @Serializable
    data object Home : Screens()

    @Serializable
    data object Login : Screens()

    @Serializable
    data object Splash : Screens()

    @Serializable
    data object OnBoarding : Screens()

    @Serializable
    data object ForgetPassword : Screens()

    @Serializable
    data class NewPassword(val email : String,val  otp : String) : Screens()

    @Serializable
    data object ActivityLog : Screens()

    @Serializable
    data object ContactUs : Screens()

    @Serializable
    data class OtpVerify(val email : String) : Screens()

    @Serializable
    data object Employees : Screens()

    @Serializable
    data object AddNewEmployee : Screens()

    @Serializable
    data class Folders(
        val id: Int,
        val name : String
    ) : Screens()

    @Serializable
    data object Profile : Screens()

    @Serializable
    data object Roles : Screens()

    @Serializable
    data object AddNewRole : Screens()

    @Serializable
    data object Sections : Screens()

    @Serializable
    data object SuccessfulPassword : Screens()
}