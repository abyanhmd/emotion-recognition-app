package com.example.emotionly.api

import android.content.Context
import android.content.SharedPreferences

class TokenPref(context: Context) {
    private val preference: SharedPreferences =
        context.getSharedPreferences(EMAIL, Context.MODE_PRIVATE)

    fun setToken(token: String, userId: String) {
        val prefEditor: SharedPreferences.Editor = preference.edit()
        prefEditor.putString(TOKEN, token)
        prefEditor.putString(USERID, userId)
        prefEditor.apply()
    }

    fun getToken(): String {
        return preference.getString(TOKEN, "").toString()
    }

    fun getUserId(): String {
        return preference.getString(USERID, "").toString()
    }

    fun removeToken() {
        val prefEditor: SharedPreferences.Editor = preference.edit()
        prefEditor.clear()
        prefEditor.apply()
    }

    companion object {
        const val EMAIL = "email"
        const val TOKEN = "token"
        const val USERID = "userid"
    }
}