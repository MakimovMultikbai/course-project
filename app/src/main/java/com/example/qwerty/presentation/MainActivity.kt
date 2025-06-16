package com.example.qwerty.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.qwerty.navigation.NavGraph
import com.example.qwerty.navigation.NavRoutes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavGraph(navController = navController, startGraph = NavRoutes.AuthGraph.route)
            val uri = intent?.data
            if (uri != null) {
                val email = uri.getQueryParameter("email")
                if (!email.isNullOrEmpty()) {
                    navController.navigate(NavRoutes.LogInNav.createRoute(email))
                }
            }

        }
    }
}




