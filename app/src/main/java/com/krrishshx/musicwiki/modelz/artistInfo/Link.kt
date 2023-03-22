package com.krrishshx.musicwiki.modelz.artistInfo

import retrofit2.http.Url

data class Link(

    @Url
    val text: String,
    val href: String,
    val rel: String
)