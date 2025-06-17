package com.example.qwerty.presentation.CardDetails

import com.example.qwerty.domain.models.Card

data class CardDetailsState(
    val card: Card? = Card(),
    val isLoading: Boolean = false,
    val completed: Boolean = false,
    val error: String? = null
)