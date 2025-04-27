package com.example.archivai.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.archivai.presentation.navigation.ArchivaiNavGraph
import com.example.archivai.presentation.navigation.NavBarItems
import com.example.archivai.presentation.navigation.Screens
import com.example.archivai.presentation.theme.AppColor
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Straight
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.utils.noRippleClickable


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ArchivAiApp() {
    var selectedIndex by remember { mutableIntStateOf(0) }

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination: NavDestination? = backStackEntry?.destination

    val showBottomNav = NavBarItems.entries.map { it.route::class }.any { route ->
        currentDestination?.hierarchy?.any {
            it.hasRoute(route)
        } == true
    }

    val paddingValues =
        androidx.compose.foundation.layout.WindowInsets.
        navigationBars.asPaddingValues()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()

            .padding(bottom = paddingValues.calculateBottomPadding()),
        containerColor = AppColor,
        bottomBar = {
            AnimatedVisibility(visible = showBottomNav,
                enter = slideInVertically(animationSpec = tween(600)) { it },
                exit = slideOutVertically(animationSpec = tween(600)) { 2 * it }) {

                AnimatedNavigationBar(
                    Modifier
                        .height(60.dp),
                    selectedIndex = selectedIndex,
                    ballAnimation = Straight(tween(300)),
                    indentAnimation = Height(tween(300)),
                    ballColor =  Color(0xFFCDAD8F),
                    barColor = AppColor,
                ) {
                    NavBarItems.entries.map { bottomNavigationItem ->
                        val isSelected =
                            currentDestination?.hierarchy?.any {
                                it.hasRoute(
                                    bottomNavigationItem.route::class
                                )
                            } == true
                        if (isSelected) selectedIndex = bottomNavigationItem.ordinal
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .noRippleClickable {
                                    selectedIndex = bottomNavigationItem.ordinal
                                    if (!isSelected) {
                                        navController.navigate(bottomNavigationItem.route) {
                                            launchSingleTop = true
                                            popUpTo(Screens.Home) {
                                                inclusive = false
                                            }
                                        }
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier.size(25.dp),
                                imageVector = if (isSelected) ImageVector.vectorResource(id = bottomNavigationItem.selectedIcon) else ImageVector.vectorResource(
                                    id = bottomNavigationItem.unselectedIcon
                                ),
                                contentDescription = "Bottom bar icon",
                                tint = if (isSelected) Color(0xFFCDAD8F)
                                else Color.LightGray
                            )

                        }

                    }

                }
            }
        }
    ) {

        Box(
            Modifier.fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 10.dp, vertical = 30.dp)
        ) {

            ArchivaiNavGraph(navController)
        }
    }


}

