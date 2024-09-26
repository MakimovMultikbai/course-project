package com.example.qwerty.data.remote

import com.example.qwerty.data.remote.dto.LoginRequest
import com.example.qwerty.data.remote.dto.RegistrationRequest
import com.example.qwerty.domain.models.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/api/account/reg")
    suspend fun reg (@Body request: RegistrationRequest): Response<TokenResponse>
    @POST("/api/account/login")
    suspend fun login (@Body request: LoginRequest): Response<TokenResponse>
}