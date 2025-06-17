package com.example.qwerty.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qwerty.domain.repository.ApplicationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: ApplicationRepository
) : ViewModel() {

    private val _state = mutableStateOf<Boolean?>(null)
    val state: State<Boolean?> = _state


    init {
        checkToken()
    }
    fun checkToken() {

        viewModelScope.launch {
            try {
                withContext(Dispatchers.Main){
                    _state.value = repository.checkToken()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main){
                    _state.value = false
                }
            }
        }
    }
}
