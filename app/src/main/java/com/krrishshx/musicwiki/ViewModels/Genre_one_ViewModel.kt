package com.krrishshx.musicwiki.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krrishshx.musicwiki.modela.GenreTopArtist
import com.krrishshx.musicwiki.modelb.GenreTopTracks
import com.krrishshx.musicwiki.modely.GenreInfo
import com.krrishshx.musicwiki.modelz.GenreTopAlbums
import com.krrishshx.musicwiki.repos.GenreRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Genre_one_ViewModel (val repo: GenreRepo): ViewModel(){

    var flag1 = 0
     var TAG = MutableLiveData<String>()

    var netwok_status = MutableLiveData<String>()


    fun callToNetwork(){

    }
    fun removeLinks(str:String) :String{
       var inde = str.indexOf("<a href=")

       var result = str.substring(0,inde)
        return result
    }


    fun getGenreInfo(tag:String) {

        viewModelScope.launch(Dispatchers.IO) {
            repo.getGenreInfoList(tag)         //telling repo to load data
        }
    }


    fun getTopAlbumsList(tag:String){

        viewModelScope.launch(Dispatchers.IO){
            repo.getGenreTopAlbums_List (tag)
        }

    }
    fun getTopArtistList(tag:String){

        viewModelScope.launch(Dispatchers.IO){
            repo.getGenreTopArtistsList(tag)
        }

    }
    fun getTopTracksList(tag:String){

        viewModelScope.launch(Dispatchers.IO){
            repo.getGenreTopTracksList(tag)
        }

    }



    val genre_Info : LiveData<GenreInfo>
        get() = repo.Genre_info               //getting data from repo


    val genre_top_albumsList :LiveData<GenreTopAlbums>
    get() = repo.Genre_top_albums

    val genre_top_artistList :LiveData<GenreTopArtist>
        get() = repo.Genre_top_artists

    val genre_top_tracksList :LiveData<GenreTopTracks>
        get() = repo.Genre_top_tracks



}