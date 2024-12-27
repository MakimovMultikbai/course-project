package com.example.qwerty.domain.repository


import com.example.qwerty.domain.models.UserData
import retrofit2.Response

interface ApplicationRepository {
    suspend fun reg (name:String, number:String, email:String, password:String)
    suspend fun logIn (email:String, password: String): String?
    suspend fun getUserData (): UserData
    suspend fun confirmationEmail (email: String)
}
