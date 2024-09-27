package com.example.qwerty.presentation.account.Registration


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qwerty.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel (){
    private val _state = mutableStateOf(SignUpState())
    val state : State<SignUpState> = _state

    fun change_username (name:String){
        _state.value = state.value.copy(username = name)
    }
    fun change_phoneNumber (number:String){
        _state.value = state.value.copy(phoneNumber = number)
    }
    fun change_email (email:String) {
        _state.value = state.value.copy(email = email)
    }
    fun change_password (pass:String) {
        _state.value = state.value.copy(password = pass)
        validate_password(pass)
        val result: Boolean = state.value.minLenght and state.value.specialChar and state.value.capitalizedChar and state.value.lowercaseСhar
        _state.value = state.value.copy(passIsValid = result)
    }
    fun sign_up () {
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                //if (_state.value.passIsValid){
                    val error = authRepository.reg(state.value.username, state.value.phoneNumber, state.value.email, state.value.password)
                    if (error.isNullOrEmpty()) {
                        _state.value = state.value.copy(isComplete = true)
                    }
                    else _state.value = state.value.copy(error = error,isLoading = false)
               //}
                //else{
                 //   _state.value = state.value.copy(isLoading = false)
               // }

            }
            catch (e: Exception){
                _state.value = state.value.copy(error = e.message.toString(),isLoading = false)
            }
        }
    }
    fun reset_error () {
        _state.value = state.value.copy(error = "")
    }
    private fun validate_password(password: String){
        _state.value = state.value.copy(minLenght = validate_minLenght(password))
        _state.value = state.value.copy(specialChar = validate_specialChar(password))
        _state.value = state.value.copy(capitalizedChar = validate_capitalizedChar(password))
        _state.value = state.value.copy(lowercaseСhar = validate_lowercaseСhar(password))
    }
    private fun validate_minLenght(password: String): Boolean = password.matches((Regex(".{8,}")))
    private fun validate_specialChar(password: String): Boolean = password.contains(Regex(pattern = "[!?*.+\\-\$#@]"))
    private fun validate_capitalizedChar(password: String): Boolean = password.matches(Regex(".*[A-Z].*"))
    private fun validate_lowercaseСhar(password: String): Boolean = password.matches(Regex(".*[a-z].*"))


}
