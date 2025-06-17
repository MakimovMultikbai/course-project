package com.example.qwerty.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.qwerty.domain.models.Card
import com.example.qwerty.presentation.AddCard.AddCardScreen
import com.example.qwerty.presentation.CardDetails.CardDetailsScreen
import com.example.qwerty.presentation.app.MainScreen
import com.google.gson.Gson


fun NavGraphBuilder.appGraph(
    navController: NavController,
    startDestination: String
) {
    navigation(startDestination, NavRoutes.AppGraph.route){
        composable(NavRoutes.MainNav.route){
            MainScreen(navController)
        }
        composable(NavRoutes.CardDetails.route + "/{card}"){
            val card = it.arguments?.getString("card")


            CardDetailsScreen(
                navController = navController,
                card = Gson().fromJson(card, Card::class.java)
            )
        }

        composable(NavRoutes.AddCard.route){
            AddCardScreen(navController)
        }
    }
}