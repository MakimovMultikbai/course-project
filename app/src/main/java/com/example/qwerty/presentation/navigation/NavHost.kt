package com.example.qwerty.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost


@Composable
fun NavGraph (navController: NavHostController, startGraph : String) {
    NavHost(navController = navController, startDestination = startGraph) {
        appGraph(navController = navController, startDestination = NavRoutes.MainNav.route)
        authGraph(navController = navController, startDestination = NavRoutes.SignUpNav.route)
    }
}