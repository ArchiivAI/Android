package com.example.archivai.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.archivai.presentation.screens.OnBoardingScreens.onBoardingScreenRoute
import com.example.archivai.presentation.screens.SplashScreen.splashScreenRoute
import com.example.archivai.presentation.screens.activity_log.activityLogScreenRoute
import com.example.archivai.presentation.screens.contact_us.contactUsScreenRoute
import com.example.archivai.presentation.screens.employees.add_new_employee.addNewEmployeeScreenRoute
import com.example.archivai.presentation.screens.employees.employeesScreen.employeesScreenRoute
import com.example.archivai.presentation.screens.folders.foldersScreenRoute
import com.example.archivai.presentation.screens.forget_password.forget_password.forgetPasswordScreenRoute
import com.example.archivai.presentation.screens.forget_password.new_password.newPasswordScreenRoute
import com.example.archivai.presentation.screens.forget_password.successful_password_change.successfulPasswordScreenRoute
import com.example.archivai.presentation.screens.home.homeScreenRoute
import com.example.archivai.presentation.screens.login_screen.loginScreenRoute
import com.example.archivai.presentation.screens.profile.profileScreenRoute
import com.example.archivai.presentation.screens.roles.add_new_role.addNewRolesScreenRoute
import com.example.archivai.presentation.screens.roles.roles_screen.rolesScreenRoute
import com.example.archivai.presentation.screens.sections.sectionsScreenRoute

@Composable
fun ArchivaiNavGraph(navController: NavHostController) {

    NavHost(navController, startDestination = Screens.Splash){
        contactUsScreenRoute(navController)
        activityLogScreenRoute(navController)
        employeesScreenRoute(navController)
        addNewRolesScreenRoute(navController)
        addNewEmployeeScreenRoute(navController)
        foldersScreenRoute(navController)
        forgetPasswordScreenRoute(navController)
        newPasswordScreenRoute(navController)
        successfulPasswordScreenRoute(navController)
        homeScreenRoute(navController)
        loginScreenRoute(navController)
        onBoardingScreenRoute(navController)
        profileScreenRoute(navController)
        rolesScreenRoute(navController)
        sectionsScreenRoute(navController)
        splashScreenRoute(navController)



    }




}