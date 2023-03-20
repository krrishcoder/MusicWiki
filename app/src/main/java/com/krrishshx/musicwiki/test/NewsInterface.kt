package com.krrishshx.musicwiki.test

import com.krrishshx.musicwiki.testmodel.modelx
import com.krrishshx.newsx.newss
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL =  "https://ws.audioscrobbler.com"
const val API_KEY =  "09db9f968c52208fac594ffcfa47ae00"

//https://newsapi.org/v2/top-headlines?country=in&apiKey=1471a6846d4d452baa343c1e34283dec

interface NewsInterface {

    //https://ws.audioscrobbler.com/2.0/?method=chart.gettoptags&api_key=09db9f968c52208fac594ffcfa47ae00&format=json

    @GET("/2.0/?method=chart.gettoptags&api_key=$API_KEY&format=json")
    fun getHeadlines(@Query("page") page:Int) : Call<modelx>

//https://newsapi.org/v2/top-headlines?apiKey=1471a6846d4d452baa343c1e34283dec&country=in&page=1

    //  https://ws.audioscrobbler.com/2.0/?method=chart.gettoptags&api_key=09db9f968c52208fac594ffcfa47ae00&format=json&page=1



}

object NewsService{
    val newsInstance:NewsInterface

    init{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}