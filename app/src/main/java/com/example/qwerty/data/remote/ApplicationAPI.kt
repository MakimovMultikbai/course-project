package com.example.qwerty.data.remote

import com.example.qwerty.data.data_source.dto.EmailConfirmRequest
import com.example.qwerty.data.data_source.dto.LoginRequest
import com.example.qwerty.data.data_source.dto.RegistrationRequest
import com.example.qwerty.domain.models.TokenResponse
import com.example.qwerty.domain.models.UserData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApplicationAPI {
    @POST("/api/v1/auth/account")
    suspend fun reg (@Body request: RegistrationRequest)
    @POST("/api/v1/auth/account/session")
    suspend fun login (@Body request: LoginRequest): Response<TokenResponse>
    @GET("/api/v1/users/current")
    suspend fun getCurrentUser(@Header("Authorization") token: String): UserData

    @POST("/api/v1/auth/account/email/confirmation-link")
    suspend fun emailConfirmation(@Body request: EmailConfirmRequest)
}