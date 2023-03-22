package com.krrishshx.musicwiki.modelz.artistInfo

import retrofit2.http.Url

data class Image(
    @Url
    val text: String,
    val size: String
)