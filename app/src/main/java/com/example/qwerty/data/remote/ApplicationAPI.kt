package com.example.qwerty.data.remote

import com.example.qwerty.data.dto.EmailConfirmRequest
import com.example.qwerty.data.dto.LoginRequest
import com.example.qwerty.data.dto.RegistrationRequest
import com.example.qwerty.domain.models.ActivateCardModel
import com.example.qwerty.domain.models.TokenResponse
import com.example.qwerty.domain.models.TransactionResponse
import com.example.qwerty.domain.models.UserData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApplicationAPI {
    @POST("/api/auth/register")
    suspend fun reg (@Body request: RegistrationRequest)
    @POST("/api/auth/token")
    suspend fun login (@Body request: LoginRequest): Response<TokenResponse>

    @GET("/api/users/me")
    suspend fun getCurrentUser(@Header("Authorization") token: String): Response<UserData>

    @POST("/api/auth/email/confirmation-link")
    suspend fun emailConfirmation(@Body request: EmailConfirmRequest)



    @PUT("/api/cards/activation")
    suspend fun activateCard(
        @Body request: ActivateCardModel,
        @Header("Authorization") token: String
    )

    @PUT("/api/cards/{id}/block")
    suspend fun blockCard(
        @Path("id") id: String,
        @Header("Authorization") token: String
    ) : Response<Unit>

    @PUT("/api/cards/{id}/freezing")
    suspend fun freezingCard(
        @Path("id") id: String,
        @Header("Authorization") token: String
    ) : Response<Unit>

    @PUT("/api/cards/{id}/unfreezing")
    suspend fun unfreezingCard(
        @Path("id") id: String,
        @Header("Authorization") token: String
    ) : Response<Unit>


    @GET("/api/cards/{id}/operations")
    suspend fun getOperations(
        @Path("id") id: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Header("Authorization") token: String
    ): Response<TransactionResponse>
}