package com.example.qwerty.presentation.account.Authorization

data class LogInState(
    val login:String = "",
    val password:String = "",
    val isLoading: Boolean = false,
    val error: String = "",
    val isComplete: Boolean = false,
)
