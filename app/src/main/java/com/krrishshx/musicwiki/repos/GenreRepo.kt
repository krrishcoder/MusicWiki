package com.krrishshx.musicwiki.repos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.krrishshx.musicwiki.api.GenreService
import com.krrishshx.musicwiki.modela.GenreTopArtist
import com.krrishshx.musicwiki.modela.artist_top_albums.Artist_top_album
import com.krrishshx.musicwiki.modela.artist_top_trackp.Artist_top_tracks
import com.krrishshx.musicwiki.modelb.GenreTopTracks
import com.krrishshx.musicwiki.modelx.topGenre
import com.krrishshx.musicwiki.modely.GenreInfo
import com.krrishshx.musicwiki.modelz.GenreTopAlbums
import com.krrishshx.musicwiki.modelz.albuminfo.AlbumInfo
import com.krrishshx.musicwiki.modelz.artistInfo.Album_Artist_Info


class GenreRepo(private val genreService: GenreService) {

    private val genreLiveData = MutableLiveData<topGenre>()
    val Genre : LiveData<topGenre>
    get() = genreLiveData


    private val genreInformationLiveData = MutableLiveData<GenreInfo>()
    val Genre_info :LiveData<GenreInfo>
    get() = genreInformationLiveData



    private val genreTop_albums_LiveData = MutableLiveData<GenreTopAlbums>()
    val Genre_top_albums :LiveData<GenreTopAlbums>
        get() = genreTop_albums_LiveData




    private val genre_Top_artistsLiveData = MutableLiveData<GenreTopArtist>()
    val Genre_top_artists :LiveData<GenreTopArtist>
        get() = genre_Top_artistsLiveData


    private val genre_Top_tracks_LiveData = MutableLiveData<GenreTopTracks>()
    val Genre_top_tracks :LiveData<GenreTopTracks>
        get() = genre_Top_tracks_LiveData




    private val album_info_LiveData = MutableLiveData<AlbumInfo>()
    val Album_info_list :LiveData<AlbumInfo>
        get() = album_info_LiveData

    private val album_artist_info_liveData = MutableLiveData<Album_Artist_Info>()
    val Album_Artist_Info_list:LiveData<Album_Artist_Info>
    get() = album_artist_info_liveData



    private val artist_topTracks_livedata = MutableLiveData<Artist_top_tracks>()
    val ArtistTopTrack_list:LiveData<Artist_top_tracks>
    get() = artist_topTracks_livedata

    private val artist_topAlbums_livedata = MutableLiveData<Artist_top_album>()
    val ArtistTopAlbum_list: LiveData<Artist_top_album>
    get() = artist_topAlbums_livedata



    suspend fun getArtist_TopTracks(artist:String){
        val result = genreService.getArtist_TopTracks(artist)
        if(result !=null && result?.body() !=null){

           artist_topTracks_livedata.postValue(result.body())
            Log.d("debug:","body is not null of get artist top tracks list ")
            Log.d("debug:","result got of  artist top tracks ${result.body()!!.toptracks.track.toString()} ")

        }else{
            Log.d("debug:","body is null of get artist top tracks list ")
        }
    }

    suspend fun getArtist_TopAlbums(artist: String){
        val result = genreService.getArtist_TopAlbums(artist)

        if(result !=null && result?.body() !=null){

            artist_topAlbums_livedata.postValue(result.body())
            Log.d("debug:","body is not null of get artist top albums list ")
            Log.d("debug:","result got of  artist top albums ${result.body()!!.topalbums.album.toString() } ")

        }else{
            Log.d("debug:","body is null of get artist top albums list ")
        }
    }




    suspend fun getAlbum_artist_InfoList(artist:String){

        val result = genreService.getAlbum_artist_Info(artist)

        if(result !=null && result?.body() !=null){

            album_artist_info_liveData.postValue(result.body())
            Log.d("debug:","body is not null of get album artist info list ")
            Log.d("debug:","result got of album artist  ${result.body()!!.artist.bio.summary} ")

        }else{
            Log.d("debug:","body is null of get album list ")
        }
    }


    suspend fun getAlbumInfoList(artist:String,album:String){

        val result = genreService.getAlbumInfo(artist,album)

        if(result !=null && result?.body() !=null){

            album_info_LiveData.postValue(result.body())
            Log.d("debug:","body is not null of get album info list ")
            Log.d("debug:","result got of album info  ${result.body()!!.album.artist} ")

        }else{
            Log.d("debug:","body is null of get album list ")
        }
    }












    suspend fun getGenreList(page:Int){

        val result = genreService.getGenre()

        if(result !=null && result?.body() !=null){

            genreLiveData.postValue(result.body())
            Log.d("debug:","body is not null of get genre list ")
            Log.d("debug:", result.body()!!.toptags.tag.toString())

        }else{
            Log.d("debug:","body is null of get genre list ")
        }
    }


    suspend fun getGenreInfoList(tag:String){

        val result = genreService.getGenreInformation(tag)

        if(result !=null && result?.body() !=null){

           genreInformationLiveData.postValue(result.body())
            Log.d("debug:","body is not null of get genre info list ")
            Log.d("debug:", result.body()!!.tag.toString())

        }else{
            Log.d("debug:","body is null of get genre info list ")
        }
    }


    suspend fun getGenreTopAlbums_List(tag:String){

        val result = genreService.getGenreTopAlbums(tag)

        if(result !=null && result?.body() !=null){

            genreTop_albums_LiveData.postValue(result.body())
            Log.d("debug:","body is not null of get genre top albums list ")
            Log.d("debug:", "albums ========= ${result.body()!!.albums.album.toString()}")

        }else{
            Log.d("debug:","body is null of get genre top albums ")
        }
    }

    suspend fun getGenreTopArtistsList(tag:String){

        val result = genreService.getGenreTopArtist(tag)

        if(result !=null && result?.body() !=null){

            genre_Top_artistsLiveData.postValue(result.body())
            Log.d("debug:","body is not null of get genre top artist list ")
            Log.d("debug:", result.body()!!.topartists.artist.toString())

        }else{
            Log.d("debug:","body is null of get genre top artist list ")
        }
    }

    suspend fun getGenreTopTracksList(tag:String){

        val result = genreService.getGenreTopTracks(tag)

        if(result !=null && result?.body() !=null){

            genre_Top_tracks_LiveData.postValue(result.body())
            Log.d("debug:","body is not null  of get genre top tracks list ")
            Log.d("debug:", result.body()!!.tracks.track.toString())

        }else{
            Log.d("debug:","body is null top tracks ")
        }
    }























}