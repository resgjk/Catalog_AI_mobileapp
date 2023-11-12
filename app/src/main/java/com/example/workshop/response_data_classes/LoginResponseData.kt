package com.example.workshop.response_data_classes

data class LoginResponseData(
    val id: Int,
    val login: String,
    val email: String,
    val user_image: String,
    val response: String
)