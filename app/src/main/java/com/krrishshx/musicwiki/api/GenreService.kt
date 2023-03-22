package com.krrishshx.musicwiki.api



import com.krrishshx.musicwiki.modela.GenreTopArtist
import com.krrishshx.musicwiki.modela.artist_top_albums.Artist_top_album
import com.krrishshx.musicwiki.modela.artist_top_trackp.Artist_top_tracks
import com.krrishshx.musicwiki.modelb.GenreTopTracks
import com.krrishshx.musicwiki.modelx.topGenre
import com.krrishshx.musicwiki.modely.GenreInfo
import com.krrishshx.musicwiki.modelz.GenreTopAlbums
import com.krrishshx.musicwiki.modelz.albuminfo.AlbumInfo
import com.krrishshx.musicwiki.modelz.artistInfo.Album_Artist_Info
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
const val BASE_URL =  "https://ws.audioscrobbler.com"
const val API_KEY =  "09db9f968c52208fac594ffcfa47ae00"

interface GenreService {


    @GET("/2.0/?method=tag.getTopTags&api_key=$API_KEY&format=json")
    suspend fun getGenre() : Response<topGenre>
    //@Query("page") page :Int

    @GET("/2.0/?method=tag.getinfo&api_key=$API_KEY&format=json")
    suspend fun getGenreInformation(@Query("tag") tag:String) : Response<GenreInfo>


    @GET("/2.0/?method=tag.gettopalbums&api_key=$API_KEY&format=json")
    suspend fun getGenreTopAlbums(@Query("tag") tag:String) : Response<GenreTopAlbums>


    @GET("/2.0/?method=tag.gettoptracks&api_key=$API_KEY&format=json")
    suspend fun getGenreTopTracks(@Query("tag") tag:String) : Response<GenreTopTracks>


    @GET("/2.0/?method=tag.gettopartists&api_key=$API_KEY&format=json")
    suspend fun getGenreTopArtist(@Query("tag") tag:String) : Response<GenreTopArtist>

    @GET("/2.0/?method=album.getinfo&api_key=$API_KEY&format=json")
    suspend fun getAlbumInfo(@Query("artist") artist:String,@Query("album") album:String) : Response<AlbumInfo>

    @GET("/2.0/?method=artist.getinfo&api_key=$API_KEY&format=json")
    suspend fun getAlbum_artist_Info(@Query("artist") artist:String) : Response<Album_Artist_Info>

    @GET("/2.0/?method=artist.gettopalbums&api_key=$API_KEY&format=json")
    suspend fun getArtist_TopAlbums(@Query("artist") artist:String) : Response<Artist_top_album>

    @GET("/2.0/?method=artist.gettoptracks&api_key=$API_KEY&format=json")
    suspend fun getArtist_TopTracks(@Query("artist") artist:String) : Response<Artist_top_tracks>





}
//https://ws.audioscrobbler.com/2.0/?method=chart.gettoptags&api_key=09db9f968c52208fac594ffcfa47ae00&format=json&page=1

//https://ws.audioscrobbler.com/2.0/?method=tag.getTopTags&api_key=09db9f968c52208fac594ffcfa47ae00&format=json