package com.example.qwerty.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
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

            val viewModel = hiltViewModel<MainActivityViewModel>()
            val state = viewModel.state.value


            if (state != null){
                NavGraph(navController = navController, startGraph = if (state == true) NavRoutes.AppGraph.route else NavRoutes.AuthGraph.route)
            }


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




