package com.krrishshx.musicwiki.repos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.krrishshx.musicwiki.api.GenreService
import com.krrishshx.musicwiki.models.TopGenreList

class GenreRepo(private val genreService: GenreService) {

    private val genreLiveData = MutableLiveData<TopGenreList>()
    val Genre : LiveData<TopGenreList>
    get() = genreLiveData

    suspend fun getGenreList(page:Int){

            val result = genreService.getGenre(1)


        if(result !=null && result?.body() !=null){

            genreLiveData.postValue(result.body())
            Log.d("debug:","body is not null")
        }else{
            Log.d("debug:","body is null")
        }
    }

}