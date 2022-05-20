package com.example.emotionly.response

import com.google.gson.annotations.SerializedName

class RegisterResponse {
    @SerializedName("error")
    lateinit var error: String

    @SerializedName("message")
    lateinit var message: String
}