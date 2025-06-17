package com.example.qwerty.presentation.CardDetails

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qwerty.domain.models.Card
import com.example.qwerty.domain.models.TransactionData
import com.example.qwerty.domain.repository.ApplicationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardDetailsViewModel @Inject constructor(
    private val repository: ApplicationRepository
) : ViewModel() {

    private val _state = mutableStateOf(CardDetailsState())
    val state: State<CardDetailsState> = _state


    private val _transactions = mutableStateListOf<TransactionData>()
    val transactions: SnapshotStateList<TransactionData> = _transactions

    fun block(){
        viewModelScope.launch {
            try {
                repository.blockCard(state.value.card?.id!!)
                updateData()
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Ошибка загрузки данных"
                )
            }
            _state.value = _state.value.copy(
                completed = true
            )
        }
    }

    fun freeze(){
        viewModelScope.launch {
            try {
                repository.freezingCard(state.value.card?.id!!)
                updateData()
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Ошибка загрузки данных"
                )
            }
            _state.value = _state.value.copy(
                completed = true
            )
        }
    }

    fun unfreeze(){
        viewModelScope.launch {
            try {
                repository.unfreezingCard(state.value.card?.id!!)

                updateData()
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Ошибка загрузки данных"
                )
            }
            _state.value = _state.value.copy(
                completed = true
            )
        }
    }

    private fun updateData(){
        viewModelScope.launch {
            try {
                val user = repository.getUserData()
                val transactions = repository.getTransactions(state.value.card?.id!!)
                _state.value = _state.value.copy(
                    card = user?.cards?.find { it.id == state.value.card?.id },
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Ошибка загрузки данных"
                )
            }
        }
    }


    fun getData(card: Card?) {
        _state.value = _state.value.copy(isLoading = true)

        _state.value = _state.value.copy(
            card = card,
            isLoading = false,
            error = null
        )
        viewModelScope.launch {
            try {
                val transactions = repository.getTransactions(card?.id!!)
                Log.i("retrofitBody", transactions.toString())
                _transactions.addAll(transactions)

            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Ошибка загрузки данных"
                )
            }
        }
    }
}
