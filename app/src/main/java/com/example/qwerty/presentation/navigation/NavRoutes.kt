package com.example.qwerty.presentation.navigation

sealed class NavRoutes(val route: String) {

    object AuthGraph : NavRoutes("AuthGraph")

    object SignUpNav : NavRoutes("SignUp")
    object LogInNav : NavRoutes("LogIn")



    object AppGraph : NavRoutes("AppGraph")

    object MainNav : NavRoutes("Main")
}
