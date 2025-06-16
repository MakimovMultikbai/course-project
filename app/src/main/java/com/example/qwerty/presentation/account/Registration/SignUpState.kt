package com.example.qwerty.presentation.account.Registration

data class SignUpState(
    val firstname:String = "",
    val lastname:String = "",
    val patronymic:String = "",
    val phoneNumber:String ="",
    val email:String = "",
    val password:String = "",
    val isLoading: Boolean = false,
    val error: String = "",
    val isComplete: Boolean = false,
    val passIsValid: Boolean = false,
    val phoneNumberIsValid: Boolean = false,
    val emailIsValid:Boolean = false,
)