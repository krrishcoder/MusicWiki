package com.krrishshx.musicwiki.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krrishshx.musicwiki.modela.artist_top_albums.Artist_top_album
import com.krrishshx.musicwiki.modela.artist_top_trackp.Artist_top_tracks
import com.krrishshx.musicwiki.modelz.artistInfo.Album_Artist_Info
import com.krrishshx.musicwiki.repos.GenreRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistViewModel(val repo: GenreRepo): ViewModel(){

    var flag1 = 0
    var ARTIST =  MutableLiveData<String>()
    var artistx =""


    fun getArtistTopTracks(artist:String) {

        viewModelScope.launch(Dispatchers.IO) {
            repo.getArtist_TopTracks(artist)       //telling repo to load data
        }
    }

    fun getArtistInfo(artist:String){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAlbum_artist_InfoList(artist)
        }
    }

    fun getArtistTopAlbums(artist: String){

        viewModelScope.launch(Dispatchers.IO) {
            repo.getArtist_TopAlbums(artist)
        }
    }

    val Artist_top_tracks_live_data : LiveData<Artist_top_tracks>
        get() = repo.ArtistTopTrack_list         //getting data from repo

    val Artist_top_album_live_data : LiveData<Artist_top_album>
        get() = repo.ArtistTopAlbum_list           //getting data from repo

    val Artist_info_LiveData : LiveData<Album_Artist_Info>
        get() = repo.Album_Artist_Info_list



}