package com.krrishshx.musicwiki.modelz.artistInfo

import com.google.gson.annotations.SerializedName
import retrofit2.http.Url

data class Image(
    @SerializedName("#text")
    val text: String,
    val size: String
)