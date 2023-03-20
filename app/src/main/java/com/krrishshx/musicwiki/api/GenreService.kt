package com.krrishshx.musicwiki.api

import com.krrishshx.musicwiki.models.TopGenreList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
const val BASE_URL =  "https://ws.audioscrobbler.com"
const val API_KEY =  "09db9f968c52208fac594ffcfa47ae00"

interface GenreService {


    @GET("/2.0/?method=chart.gettoptags&api_key=$API_KEY&format=json")
    suspend fun getGenre(@Query("page") page:Int) : Response<TopGenreList>
    //@Query("page") page :Int
}