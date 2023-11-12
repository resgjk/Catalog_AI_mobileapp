package com.example.workshop.user

import com.example.workshop.response_data_classes.LoginResponseData
import com.example.workshop.response_data_classes.RegisterResponseData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiMethods {
    @POST("/api/v1/user/register")
    suspend fun registerUser(@Body user: UserModelRegister): Response<RegisterResponseData>

    @POST("/api/v1/user/login")
    suspend fun loginUser(@Body user: UserModelLogin): Response<LoginResponseData>
}