package com.example.qwerty.domain.models

import com.google.gson.annotations.SerializedName

data class ActivateCardModel(
    @SerializedName("cardNumber")
    val cardNumber: String = "",

    @SerializedName("cardPin")
    val cardPin: String = ""
)

