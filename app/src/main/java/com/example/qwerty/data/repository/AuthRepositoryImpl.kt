package com.example.qwerty.data.repository

import android.util.Log
import androidx.compose.ui.res.stringArrayResource
import com.example.qwerty.data.data_source.TokensStorage
import com.example.qwerty.data.remote.dto.LoginRequest
import com.example.qwerty.data.remote.dto.RegistrationRequest
import com.example.qwerty.data.remote.AuthApi
import com.example.qwerty.domain.models.TokenResponse
import com.example.qwerty.domain.repository.AuthRepository
import org.json.JSONObject

class AuthRepositoryImpl (val api: AuthApi, val localStorage: TokensStorage) : AuthRepository {
    override suspend fun reg(
        name: String,
        number: String,
        email: String,
        password: String
    ): String? {
        val response =  api.reg(RegistrationRequest(userName = name, phoneNumber = number, email = email,password = password))
        if (response.isSuccessful){
            localStorage.saveToken(response?.body()?.token)
            localStorage.saveRefToken(response?.body()?.refreshToken)
            return null
        }
        else {
            val error = response.errorBody()?.string()
            Log.i("errorbody", error.toString())
            val answer = JSONObject(error)
            val result = answer.getString("errors")
            val result2 = JSONObject(result)
            val result3 = result2.getJSONArray("Password")

            var string = ""
            for (i in 0..result3.length()-1){
                string += "${result3[i]}\n"
            }
            return string
            }
        }


    override suspend fun logIn(email: String, password: String): String? {
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
}