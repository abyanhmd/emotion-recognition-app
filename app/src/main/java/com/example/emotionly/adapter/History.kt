package com.example.emotionly.adapter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class History (
    var emotion: String? = null,
    var dateTaken: Date,
    var duration: String,
    var suggestion: String? = null,
    var fileName: String
): Parcelable