package com.krrishshx.musicwiki.modela.artist_top_trackp

import com.google.gson.annotations.SerializedName

data class Image(

    @SerializedName("#text")
    val text: String,
    val size: String
)