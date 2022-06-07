package com.example.emotionly.response

import com.google.gson.annotations.SerializedName
import java.util.*

data class HistoryResponse (
    @SerializedName("emotion")
    var emotion: String? = null,

    @SerializedName("dateTaken")
    var dateTaken: Date,

    @SerializedName("duration")
    var duration: String,

    @SerializedName("suggestion")
    var suggestion: String? = null,

    @SerializedName("fileName")
    var fileName: String,

    @SerializedName("audioFile")
    var audioFile: String
)