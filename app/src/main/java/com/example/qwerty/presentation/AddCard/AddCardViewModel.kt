package com.example.qwerty.presentation.AddCard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qwerty.domain.models.ActivateCardModel
import com.example.qwerty.domain.repository.ApplicationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCardViewModel @Inject constructor(
    private val repository: ApplicationRepository
) : ViewModel() {

    private val _state = mutableStateOf(AddCardState())
    val state: State<AddCardState> = _state


    fun redactCardNumber(
        number: String
    ){
        _state.value = _state.value.copy(
            cardNumber = number
        )
    }


    fun redactCode(
        code: String
    ){
        if (code.length <= 4){
            _state.value = _state.value.copy(
                code = code
            )
        }

    }


    fun addCard() {
        _state.value = _state.value.copy(isLoading = true)

        if (state.value.code.length != 4){
            _state.value = _state.value.copy(
                isLoading = false,
                error = "Длина кода должна быть 4 символа"
            )
        }
        viewModelScope.launch {
            try {
                repository.activateCard(ActivateCardModel(
                    state.value.cardNumber,
                    state.value.code
                ))
                _state.value = _state.value.copy(
                    isLoading = false,
                    completed = true
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Ошибка отправки запроса"
                )
            }
        }
    }
}
