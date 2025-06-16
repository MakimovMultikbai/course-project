package com.example.qwerty.presentation.account.Authorization

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qwerty.domain.repository.ApplicationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(private val applicationRepository: ApplicationRepository): ViewModel(){
    private val _state = mutableStateOf(LogInState())
    val state : State<LogInState> = _state

    fun change_email (login:String) {
        _state.value = state.value.copy(login = login)
    }

    fun change_password (pass:String) {
        _state.value = state.value.copy(password = pass)
    }

    fun log_in () {
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                applicationRepository.logIn(state.value.login, state.value.password)
                _state.value = state.value.copy(isComplete = true)
            }
            catch (e: Exception){
                _state.value = state.value.copy(error = e.message.toString(),isLoading = false)
            }
        }
    }
}
