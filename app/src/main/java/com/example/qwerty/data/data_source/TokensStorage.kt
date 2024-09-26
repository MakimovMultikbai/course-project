package com.example.qwerty.data.data_source

import android.content.Context

class TokensStorage (val context: Context) {
    private val storage = context.getSharedPreferences("Tokens",Context.MODE_PRIVATE)

    fun saveToken (token:String?){
        storage.edit()
            .putString("token", token)
            .commit()
    }

    fun getToken(): String? {
        return storage.getString("token", null)
    }

    fun saveRefToken(reftoken:String?){
        storage.edit()
            .putString("reftoken", reftoken)
            .commit()
    }

    fun getRefToken(): String?{
        return storage.getString("reftoken", null)
    }
}