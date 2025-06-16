package com.example.qwerty.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
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

        composable(
            route = NavRoutes.LogInNav.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "https://192.168.0.142:8082/api/v1/auth/account/email/confirmation?email={email}"
                }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            LogInScreen(navController, preFilledEmail = email)
        }
    }
}