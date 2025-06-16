package com.example.qwerty.domain.models

data class TokenResponse (
    val accessToken:String,
    val refreshToken:String,
    val tokenType:String,
    val expiresIn: Int,
)