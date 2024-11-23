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
        if (number.length <= 10 &&
            !number.matches(Regex(".*[^0-9].*")) ||
            number.isEmpty()
            ) {
                _state.value = state.value.copy(phoneNumber = number)
        }
    }
    fun change_email (email:String) {
        if (!email.contains(Regex("\\s"))) {
            _state.value = state.value.copy(emailIsValid = validate_email(email))
            _state.value = state.value.copy(email = email)
        }
    }
    fun change_password (pass:String) {
        _state.value = state.value.copy(password = pass)
        _state.value = state.value.copy(passIsValid = validate_password(pass))
    }
    fun sign_up () {
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                    val error = authRepository.reg(state.value.username, state.value.phoneNumber, state.value.email, state.value.password)
                    if (error.isNullOrEmpty()) {
                        _state.value = state.value.copy(isComplete = true)
                    }
                    else _state.value = state.value.copy(error = error,isLoading = false)
            }
            catch (e: Exception){
                _state.value = state.value.copy(error = e.message.toString(),isLoading = false)
            }
        }
    }
    fun reset_error () {
        _state.value = state.value.copy(error = "")
    }
    private fun validate_password(password: String): Boolean =
                validate_minLenght(password) and
                validate_specialChar(password) and
                validate_capitalizedChar(password) and
                validate_lowercaseChar(password) and
                validate_number(password)
    private fun validate_email(email: String): Boolean = email.matches(Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))
    private fun validate_minLenght(string: String): Boolean = string.matches((Regex(".{8,}")))
    private fun validate_specialChar(password: String): Boolean = password.contains(Regex(pattern = "[!?*.+\\-\$#@]"))
    private fun validate_capitalizedChar(password: String): Boolean = password.matches(Regex(".*[A-Z].*"))
    private fun validate_lowercaseChar(password: String): Boolean = password.matches(Regex(".*[a-z].*"))
    private fun validate_number(password: String): Boolean = password.matches(Regex(".*[0-9].*"))
}
