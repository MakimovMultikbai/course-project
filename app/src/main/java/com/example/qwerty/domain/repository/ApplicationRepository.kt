package com.example.qwerty.domain.repository


import com.example.qwerty.domain.models.UserData

interface ApplicationRepository {
    suspend fun reg (firstname:String, lastname:String, patronymic:String, number:String, email:String, password:String)
    suspend fun logIn (login:String, password: String): String?
    suspend fun getUserData (): UserData?
    suspend fun confirmationEmail (email: String)
}
