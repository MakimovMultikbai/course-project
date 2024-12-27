package com.example.qwerty.domain.models

data class Card (
    val id: Int,
    val isActivated: Boolean,
    val number: Int,
    val discountPercent: Int,
    val bonusSystemTitle: String
)

