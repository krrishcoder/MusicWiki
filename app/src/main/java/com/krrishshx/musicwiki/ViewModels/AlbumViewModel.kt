package com.krrishshx.musicwiki.ViewModels

import androidx.lifecycle.*
import com.krrishshx.musicwiki.modela.GenreTopArtist
import com.krrishshx.musicwiki.modelb.GenreTopTracks
import com.krrishshx.musicwiki.modely.GenreInfo
import com.krrishshx.musicwiki.modelz.GenreTopAlbums
import com.krrishshx.musicwiki.modelz.albuminfo.AlbumInfo
import com.krrishshx.musicwiki.modelz.artistInfo.Album_Artist_Info
import com.krrishshx.musicwiki.repos.GenreRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class AlbumViewModel (val repo: GenreRepo): ViewModel(){

    var flag1 = 0
    var NAME = MutableLiveData<String>()
    var ARTIST =  MutableLiveData<String>()


    fun getAlbumInfo(album:String,artist:String) {

        viewModelScope.launch(Dispatchers.IO) {
            repo.getAlbumInfoList(artist,album)        //telling repo to load data
        }
    }

    fun getAlbumArtistInfo(artist:String){
        viewModelScope.launch(IO) {
            repo.getAlbum_artist_InfoList(artist)
        }
    }

    val Album_Info : LiveData<AlbumInfo>
        get() = repo.Album_info_list             //getting data from repo

    val Album_Artist_info_LiveData :LiveData<Album_Artist_Info>
    get() = repo.Album_Artist_Info_list


}
