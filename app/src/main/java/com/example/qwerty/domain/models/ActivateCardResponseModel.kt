package com.example.qwerty.domain.models

import com.google.gson.annotations.SerializedName

data class ActivateCardResponseModel(
    @SerializedName("cardNumber")
    val activatedCardId: String = "",

    @SerializedName("cardPin")
    val cardPin: String = ""
)

