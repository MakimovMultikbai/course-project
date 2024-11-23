package com.example.qwerty.data.remote

import com.example.qwerty.di.dto.LoginRequest
import com.example.qwerty.di.dto.RegistrationRequest
import com.example.qwerty.domain.models.TokenResponse
import com.example.qwerty.domain.models.UserData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @POST("/api/v1/account/reg")
    suspend fun reg (@Body request: RegistrationRequest): Response<TokenResponse>
    @POST("/api/v1/auth/account/session")
    suspend fun login (@Body request: LoginRequest): Response<TokenResponse>
    @GET("/api/v1/users/current")
    suspend fun getCurrentUser(): Response<UserData>
}