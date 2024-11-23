package com.example.qwerty.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.qwerty.domain.repository.app.MainScreen


fun NavGraphBuilder.appGraph(
    navController: NavController,
    startDestination: String
) {
    navigation(startDestination, NavRoutes.AppGraph.route){
        composable(NavRoutes.MainNav.route){
            MainScreen(navController)
        }
    }


}