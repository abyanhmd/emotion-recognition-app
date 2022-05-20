package com.example.emotionly.response

import com.google.gson.annotations.SerializedName

class UserRequest {
    @SerializedName("name")
    lateinit var name: String

    @SerializedName("username")
    lateinit var email: String

    @SerializedName("password")
    lateinit var password: String
}