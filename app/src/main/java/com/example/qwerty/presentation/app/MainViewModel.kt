package com.example.qwerty.presentation.app

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qwerty.domain.models.UserData
import com.example.qwerty.domain.models.data_source.TokensStorage
import com.example.qwerty.domain.repository.ApplicationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ApplicationRepository
) : ViewModel() {

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    fun getCurrentUser() {
        _state.value = _state.value.copy(isLoading = true)

        viewModelScope.launch {
            try {
                val user = repository.getUserData()
                _state.value = _state.value.copy(
                    user = user ?: UserData(),
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

    fun exit(context: Context) {
        try {
            TokensStorage(context).saveToken(null)
        }catch (e: Exception){
            _state.value = _state.value.copy(
                isLoading = false,
                error = e.message ?: "Ошибка выхода"
            )
        }
    }
}
