package com.example.archivai.core.navigation

sealed class NavRoutes(val route : String) {
    object Home : NavRoutes("home")
    object Splash : NavRoutes("splash")
    object OnBoarding1 : NavRoutes("onBoarding1")
    object OnBoarding2 : NavRoutes("onBoarding2")
    object OnBoarding3 : NavRoutes("onBoarding3")
    object Sections : NavRoutes("sections")
    object Employees : NavRoutes("employees")
    object Roles : NavRoutes("roles")
    object Profile : NavRoutes("profile")


}