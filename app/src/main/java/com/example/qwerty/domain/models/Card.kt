package com.example.qwerty.domain.models

import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName("id")
    val id: String = "",

    @SerializedName("status")
    val status: String = "",

    @SerializedName("number")
    val number: String = "",

    @SerializedName("balance")
    val balance: Int = 0
)

