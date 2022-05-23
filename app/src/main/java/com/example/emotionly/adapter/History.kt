package com.example.emotionly.adapter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class History (
    var emotion: String,
    var duration: String,
    var date: String
): Parcelable