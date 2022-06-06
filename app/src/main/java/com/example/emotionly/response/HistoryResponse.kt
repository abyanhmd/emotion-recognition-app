package com.example.emotionly.response

import com.google.gson.annotations.SerializedName
import java.util.*

class HistoryResponse {
    @SerializedName("emotion")
    var emotion: String? = null

    @SerializedName("dateTaken")
    lateinit var dateTaken: Date

    @SerializedName("duration")
    lateinit var duration: String

    @SerializedName("suggestion")
    var suggestion: String? = null

    @SerializedName("fileName")
    lateinit var fileName: String

    @SerializedName("audioFile")
    lateinit var audioFile: String
}