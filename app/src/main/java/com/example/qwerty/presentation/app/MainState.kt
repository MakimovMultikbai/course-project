package com.example.qwerty.presentation.app

import com.example.qwerty.domain.models.UserData

data class MainState(
    val user: UserData = UserData(),
    val isLoading: Boolean = false,
    val error: String? = null
)