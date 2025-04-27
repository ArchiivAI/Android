package com.example.archivai.presentation.screens.sections.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BottomNavItem(
    @DrawableRes val iconRes: Int,
    @StringRes val labelRes: Int,
    val route : String)