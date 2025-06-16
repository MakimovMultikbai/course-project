package com.example.qwerty.data.dto

data class RegistrationRequest(
   val phoneNumber: String,
   val email: String,
   val password: String,
   val firstName: String,
   val lastName: String,
   val patronymic: String
)

