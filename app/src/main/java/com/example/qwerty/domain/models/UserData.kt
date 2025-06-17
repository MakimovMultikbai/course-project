package com.example.qwerty.domain.models

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("id")
    val id: String = "",

    @SerializedName("userName")
    val userName: String = "",

    @SerializedName("firstName")
    val firstName: String = "",

    @SerializedName("lastName")
    val lastName: String = "",

    @SerializedName("patronymic")
    val patronymic: String = "",

    @SerializedName("dayOfBirth")
    val dayOfBirth: BirthdayData? = null,

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
//    private fun parseDayOfBirth(dateStr: String?): DayOfBirth? {
//        if (dateStr.isNullOrEmpty()) return null
//
//        return try {
//            val parts = dateStr.split("-")
//            if (parts.size == 3) {
//                BirthdayData(
//                    year = parts[0].toIntOrNull() ?: 0,
//                    month = parts[1].toIntOrNull() ?: 0,
//                    day = parts[2].toIntOrNull() ?: 0
//                )
//            } else {
//                null
//            }
//        } catch (e: Exception) {
//            null
//        }
//    }
}