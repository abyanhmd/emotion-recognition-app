package com.example.emotionly.response

import com.google.gson.annotations.SerializedName

class UserResponse {
    @SerializedName("name")
    lateinit var name: String

    @SerializedName("email")
    lateinit var email: String

    @SerializedName("avatar")
    lateinit var avatar: String
}