package com.krrishshx.musicwiki.modela

data class Artist(
    val attr: AttrX,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val streamable: String,
    val url: String
)