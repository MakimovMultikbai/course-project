package com.example.qwerty.data.repository

import android.util.Log
import com.example.qwerty.data.data_source.TokensStorage
import com.example.qwerty.data.data_source.dto.EmailConfirmRequest
import com.example.qwerty.data.data_source.dto.LoginRequest
import com.example.qwerty.data.data_source.dto.RegistrationRequest
import com.example.qwerty.data.remote.ApplicationAPI
import com.example.qwerty.domain.models.UserData
import com.example.qwerty.domain.repository.ApplicationRepository
import org.json.JSONObject
import retrofit2.Response

class ApplicationRepositoryImpl (val api: ApplicationAPI, val localStorage: TokensStorage) : ApplicationRepository {
    override suspend fun reg(
        name: String,
        number: String,
        email: String,
        password: String
    ) {
        val response =  api.reg(
            RegistrationRequest(
            userName = name,
            phoneNumber = number,
            email = email,
            password = password)
        )
        }


    override suspend fun logIn(
        email: String,
        password: String): String? {
        val response =  api.login(LoginRequest(email = email,password = password))
        if (response.isSuccessful){
            localStorage.saveToken(response?.body()?.token)
            localStorage.saveRefToken(response?.body()?.refreshToken)
            return null
        }
        else {
            val answer = JSONObject(response.errorBody()?.string())
            return answer.getString("detail")
        }
    }

    override suspend fun getUserData(): UserData {
        return api.getCurrentUser(token = "Bearer " + localStorage.getToken().toString())
    }

    override suspend fun confirmationEmail(email: String) {
        api.emailConfirmation(EmailConfirmRequest(email = email))
    }
}