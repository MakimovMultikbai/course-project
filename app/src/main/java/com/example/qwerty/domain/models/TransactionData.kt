package com.example.qwerty.domain.models

import com.google.gson.annotations.SerializedName
import java.util.Date


data class TransactionResponse(
    @SerializedName("operations")
    val operations: List<TransactionData> = emptyList()
)

data class TransactionData(
    @SerializedName("id")
    val id: String = "",

    @SerializedName("isPurchase")
    val isPurchase: Boolean = true,

    @SerializedName("total")
    val total: Double = 0.0,

    @SerializedName("createdAt")
    val createdAt: Date = Date()
)

