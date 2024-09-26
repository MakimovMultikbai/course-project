package com.example.qwerty.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.qwerty.presentation.account.Authorization.LogInScreen
import com.example.qwerty.presentation.account.Registration.SignUpScreen


fun NavGraphBuilder.authGraph(
    navController: NavController,
    startDestination: String
) {
    navigation(startDestination, NavRoutes.AuthGraph.route){
        composable(NavRoutes.SignUpNav.route){
            SignUpScreen(navController)
        }

        composable(NavRoutes.LogInNav.route){
            LogInScreen(navController)
        }
    }


}