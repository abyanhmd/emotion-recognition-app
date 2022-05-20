package com.example.emotionly.response

import com.google.gson.annotations.SerializedName

class LoginResponse {
    @SerializedName("error")
    lateinit var error: String

    @SerializedName("message")
    lateinit var message: String

    @SerializedName("token")
    lateinit var token: String
}