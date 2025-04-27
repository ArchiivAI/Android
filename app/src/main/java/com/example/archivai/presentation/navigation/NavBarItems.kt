package com.example.archivai.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

enum class NavBarItems(
    @DrawableRes val unselectedIcon: Int,
    @DrawableRes val selectedIcon: Int,
    val route: Screens)
 {
    HOME(
        unselectedIcon = com.example.archivai.R.drawable.home_nav_unselected,
        selectedIcon = com.example.archivai.R.drawable.home_nav_selected,
        route = Screens.Home
    ),
     SECTIONS(
        unselectedIcon = com.example.archivai.R.drawable.sections_nav_unselected,
        selectedIcon = com.example.archivai.R.drawable.sections_nav_selected,
        route = Screens.Sections
    ),
     ROLES(
     unselectedIcon = com.example.archivai.R.drawable.roles_nav_unselected,
     selectedIcon = com.example.archivai.R.drawable.rules_nav_selected,
     route = Screens.Roles
     ),
     EMPLOYEES(
        unselectedIcon = com.example.archivai.R.drawable.employees_nav_unselected,
        selectedIcon = com.example.archivai.R.drawable.employees_nav_selected,
        route = Screens.Employees
    )




}