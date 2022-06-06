package com.example.emotionly.api

import com.example.emotionly.adapter.History
import com.example.emotionly.response.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("signup")
    fun registerUser(
        @Body registerRequest: UserRequest
    ): Call<RegisterResponse>

    @POST("login")
    fun loginUser(
        @Body loginRequest: UserRequest
    ): Call<LoginResponse>

    @POST("history")
    fun getHistory(
       @Header("Authorization") value: String
    ): Call<History>

    @PUT("user")
    fun renameUser(
        @Header("Authorization") value: String,
        @Body name: String
    ): Call<HistoryResponse>

    @GET("user/{id}/")
    fun getUser(
        @Header("Authorization") value: String,
        @Path("id") id: String
    ): Call<UserResponse>
}