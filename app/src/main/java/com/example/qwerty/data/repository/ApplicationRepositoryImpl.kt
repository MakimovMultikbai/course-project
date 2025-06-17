package com.example.qwerty.data.repository

import android.util.Log
import com.example.qwerty.data.dto.EmailConfirmRequest
import com.example.qwerty.data.dto.LoginRequest
import com.example.qwerty.data.dto.RegistrationRequest
import com.example.qwerty.data.remote.ApplicationAPI
import com.example.qwerty.domain.models.ActivateCardModel
import com.example.qwerty.domain.models.TransactionData
import com.example.qwerty.domain.models.UserData
import com.example.qwerty.domain.models.data_source.TokensStorage
import com.example.qwerty.domain.repository.ApplicationRepository
import org.json.JSONObject

class ApplicationRepositoryImpl (val api: ApplicationAPI, val localStorage: TokensStorage) : ApplicationRepository {
    override suspend fun reg(
        firstname: String,
        lastname: String,
        patronymic: String,
        number: String,
        email: String,
        password: String
    ) {
        val response =  api.reg(
            RegistrationRequest(
            firstName = firstname,
            lastName = lastname,
            patronymic = patronymic,
            phoneNumber = number,
            email = email,
            password = password)
        )
        }


    override suspend fun logIn(
        login: String,
        password: String): String? {
        val response =  api.login(LoginRequest(login = login, password = password))
        if (response.isSuccessful){
            localStorage.saveToken(response.body()?.accessToken)
            localStorage.saveRefToken(response.body()?.refreshToken)
            return null
        }
        else {
            val answer = JSONObject(response.errorBody()?.string().toString())
            throw Exception(answer.getString("title"))
        }
    }

    override suspend fun getUserData(): UserData? {
        val response = api.getCurrentUser(token = "Bearer " + localStorage.getToken().toString())
        Log.i("retrofitBody", response.body().toString())
        Log.i("retrofitBody", response.errorBody()?.string().toString())
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    override suspend fun confirmationEmail(email: String) {
        api.emailConfirmation(EmailConfirmRequest(email = email))
    }

    override suspend fun checkToken(): Boolean {
        val response = api.getCurrentUser(token = "Bearer " + localStorage.getToken().toString())

        return response.isSuccessful
    }

    override suspend fun activateCard(activateCardModel: ActivateCardModel) {
        api.activateCard(
            activateCardModel,
            "Bearer " + localStorage.getToken().toString()
        )
    }

    override suspend fun blockCard(cardId: String) {
        api.blockCard(
            cardId,
            "Bearer " + localStorage.getToken().toString()
        )
    }

    override suspend fun freezingCard(cardId: String) {
        api.freezingCard(
            cardId,
            "Bearer " + localStorage.getToken().toString()
        )
    }

    override suspend fun unfreezingCard(cardId: String) {
        api.unfreezingCard(
            cardId,
            "Bearer " + localStorage.getToken().toString()
        )
    }

    override suspend fun getTransactions(cardId: String): List<TransactionData> {
        val body = api.getOperations(
            cardId,
            1,
            100,
            "Bearer " + localStorage.getToken().toString()
        ).body()

        return body!!.operations
    }
}