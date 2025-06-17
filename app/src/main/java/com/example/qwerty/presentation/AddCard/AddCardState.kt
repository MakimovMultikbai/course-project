package com.example.qwerty.presentation.AddCard

data class AddCardState(
    val cardNumber: String = "",
    val code: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val completed: Boolean? = false
)