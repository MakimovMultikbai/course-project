package com.example.qwerty.presentation.account.Registration

data class SignUpState(
    val username:String = "",
    val phoneNumber:String ="",
    val email:String = "",
    val password:String = "",
    val isLoading: Boolean = false,
    val error: String = "",
    val isComplete: Boolean = false,
)
