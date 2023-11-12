package com.example.workshop.user

data class UserModelRegister(
    val login: String,
    val email: String,
    val password: String
)

data class UserModelLogin(
    val login: String,
    val password: String
)

data class FullUserModel(
    val id: Int,
    val login: String,
    val email: String,
    val userImage: String,
)