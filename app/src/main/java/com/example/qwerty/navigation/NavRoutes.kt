package com.example.qwerty.navigation

sealed class NavRoutes(val route: String) {
    object AuthGraph : NavRoutes("AuthGraph")
    object SignUpNav : NavRoutes("SignUp")
    object LogInNav : NavRoutes("LogIn"){
        fun createRoute(email: String) = "LogIn?email=$email"
    }
    object AppGraph : NavRoutes("AppGraph")
    object MainNav : NavRoutes("Main")
}
