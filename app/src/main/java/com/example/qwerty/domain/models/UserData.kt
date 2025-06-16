package com.example.qwerty.domain.models

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("id")
    val id: String = "",

    @SerializedName("name")
    val name: String = "",

    @SerializedName("dayOfBirth")
    private val dayOfBirthString: String? = null,

    @SerializedName("phoneNumber")
    val phoneNumber: String = "",

    @SerializedName("email")
    val email: String = "",

    @SerializedName("phoneNumberConfirmed")
    val phoneNumberConfirmed: Boolean = false,

    @SerializedName("twoFactorEnabled")
    val twoFactorEnabled: Boolean = false,

    @SerializedName("cards")
    val cards: List<Card> = emptyList()
) {
    val dayOfBirth: DayOfBirth?
        get() = parseDayOfBirth(dayOfBirthString)

    data class DayOfBirth(
        val year: Int = 0,
        val month: Int = 0,
        val day: Int = 0
    )
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
    private fun parseDayOfBirth(dateStr: String?): DayOfBirth? {
        if (dateStr.isNullOrEmpty()) return null

        return try {
            val parts = dateStr.split("-")
            if (parts.size == 3) {
                DayOfBirth(
                    year = parts[0].toIntOrNull() ?: 0,
                    month = parts[1].toIntOrNull() ?: 0,
                    day = parts[2].toIntOrNull() ?: 0
                )
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}