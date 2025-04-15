package com.example.archivai.sections.presentation.components


import com.example.archivai.R
import com.example.archivai.core.navigation.NavRoutes

val bottomNavItems = listOf(
    BottomNavItem(
        iconRes = R.drawable.home_nav_unselected,
        labelRes = R.string.nav_home,
        route = NavRoutes.Home.route

    ),
    BottomNavItem(
        iconRes = R.drawable.sections_nav_selected,
        labelRes = R.string.nav_sections,
        route = NavRoutes.Sections.route
    ),
    BottomNavItem(
        iconRes = R.drawable.roles_nav_unselected,
        labelRes = R.string.nav_roles,
        route = NavRoutes.Roles.route
    ),
    BottomNavItem(
        iconRes = R.drawable.employees_nav_unselected,
        labelRes = R.string.nav_employees,
        route = NavRoutes.Employees.route
    )
)