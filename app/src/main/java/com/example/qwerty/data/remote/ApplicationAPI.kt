package com.example.qwerty.data.remote

import com.example.qwerty.data.dto.EmailConfirmRequest
import com.example.qwerty.data.dto.LoginRequest
import com.example.qwerty.data.dto.RegistrationRequest
import com.example.qwerty.domain.models.TokenResponse
import com.example.qwerty.domain.models.UserData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApplicationAPI {
    @POST("/api/auth/register")
    suspend fun reg (@Body request: RegistrationRequest)
    @POST("/api/auth/token")
    suspend fun login (@Body request: LoginRequest): Response<TokenResponse>
    @GET("/api/v1/users/me")
    suspend fun getCurrentUser(@Header("Authorization") token: String): Response<UserData>

    @POST("/api/v1/auth/account/email/confirmation-link")
    suspend fun emailConfirmation(@Body request: EmailConfirmRequest)
}