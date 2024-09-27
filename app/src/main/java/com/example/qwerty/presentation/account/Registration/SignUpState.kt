package com.example.qwerty.presentation.account.Registration

data class SignUpState(
    val username:String = "",
    val phoneNumber:String ="",
    val email:String = "",
    val password:String = "",
    val isLoading: Boolean = false,
    val error: String = "",
    val isComplete: Boolean = false,

    val minLenght: Boolean = false,
    val specialChar: Boolean = false,
    val capitalizedChar: Boolean = false,
    val lowercaseСhar: Boolean = false,
    val hasNumber: Boolean = false,

    val passIsValid: Boolean = false,

)
