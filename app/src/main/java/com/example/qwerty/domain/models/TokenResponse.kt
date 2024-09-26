package com.example.qwerty.domain.models

data class TokenResponse (
    val token:String,
    val tokenType:String,
    val expiresIn: Int,
    val refreshToken:String,
)