package com.example.emotionly.api

import com.example.emotionly.response.LoginResponse
import com.example.emotionly.response.RegisterResponse
import com.example.emotionly.response.UserRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    fun registerUser(
        @Body registerRequest: UserRequest
    ): Call<RegisterResponse>

    @POST("login")
    fun loginUser(
        @Body loginRequest: UserRequest
    ): Call<LoginResponse>
}