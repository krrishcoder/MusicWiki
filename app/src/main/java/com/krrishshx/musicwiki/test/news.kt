package com.krrishshx.musicwiki.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.krrishshx.musicwiki.R
import com.krrishshx.musicwiki.testmodel.modelx
import com.krrishshx.newsx.newss
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class news : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        //https://newsapi.org/v2/top-headlines?country=us&apiKey=1471a6846d4d452baa343c1e34283dec

        getNews()
    }

    private fun getNews() {
          val news = NewsService.newsInstance.getHeadlines(1)
        news.enqueue(object : Callback<modelx>{
            override fun onFailure(call: Call<modelx>, t: Throwable) {
             Log.d("debug:","exception")
            }

            override fun onResponse(call: Call<modelx>, response: Response<modelx>) {
                val news = response.body()

                if(news != null){
                    Log.d("debug:",news.toString())
                }

            }
        })
    }
}