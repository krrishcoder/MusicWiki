package com.krrishshx.musicwiki

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.krrishshx.musicwiki.ViewModels.AlbumViewModel
import com.krrishshx.musicwiki.ViewModels.Album_vmf
import com.krrishshx.musicwiki.ViewModels.ArtistViewModel
import com.krrishshx.musicwiki.ViewModels.Artist_vmf
import com.krrishshx.musicwiki.adapters.rv_adapter_genres
import com.krrishshx.musicwiki.adapters.rv_adapter_top_albums
import com.krrishshx.musicwiki.adapters.rv_adapter_top_tracks
import com.krrishshx.musicwiki.api.GenreService
import com.krrishshx.musicwiki.api.RetrofitHelper
import com.krrishshx.musicwiki.databinding.ActivityArtistBinding
import com.krrishshx.musicwiki.modelb.TopTrack_a
import com.krrishshx.musicwiki.modelz.TopAlbums_a
import com.krrishshx.musicwiki.network.NetwokConnectivityObserver
import com.krrishshx.musicwiki.repos.GenreRepo

class Artist_activity : AppCompatActivity() , rv_adapter_genres.OnItemClickListener , rv_adapter_top_albums.OnItemClickListenerx , rv_adapter_top_tracks.OnItemClickListenery{

    lateinit var binding:ActivityArtistBinding
    lateinit var vm:ArtistViewModel
    private val adapter_genre by lazy { rv_adapter_genres(this,this) }

    lateinit var adapter_album : rv_adapter_top_albums
    lateinit var adapter_tracks : rv_adapter_top_tracks

    public   lateinit var list_my_album:ArrayList<TopAlbums_a>
             lateinit var list_my_tracks:ArrayList<TopTrack_a>
    var tagx:String =""

    lateinit var conectionLiveData : NetwokConnectivityObserver

   lateinit var arr_top_album :ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding = DataBindingUtil.setContentView(this,R.layout.activity_artist)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )

        }

        conectionLiveData = NetwokConnectivityObserver(this)

        binding.rvTopAlbumsInArtist.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        adapter_album =rv_adapter_top_albums(this,this)
        binding.rvTopAlbumsInArtist.adapter = adapter_album

        binding.rvTopTracksInArtist.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        adapter_tracks = rv_adapter_top_tracks(this,this)
        binding.rvTopTracksInArtist.adapter = adapter_tracks


        list_my_album = ArrayList<TopAlbums_a>()
        list_my_tracks = ArrayList<TopTrack_a>()


        val genreService = RetrofitHelper.getInstance().create(GenreService::class.java)
        val repo = GenreRepo(genreService)
        vm = ViewModelProvider(this, Artist_vmf(repo)).get(ArtistViewModel::class.java)

        //http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist=Cher&api_key=09db9f968c52208fac594ffcfa47ae00


        //==========================================================================================================================



        binding.rvGenresArtistActivity.adapter = adapter_genre
        binding.rvGenresArtistActivity.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,false)

        vm.ARTIST.postValue(intent.getStringExtra("artist")!!)

        vm.ARTIST.observe(this, Observer {
            if(vm.flag1==1){
                vm.getArtistInfo(it.toString())
                vm.getArtistTopAlbums(it.toString())
                vm.getArtistTopTracks(it.toString())
            }

            binding.tvTitleArtist.text = it.toString()
            vm.artistx = it.toString()

        })

        conectionLiveData.observe(this){
            if(it){
                Log.d("debug::","network is 1  ${it.toString()}")
                vm.flag1=1
                //  vm.TAG.postValue(vm.TAG.value)
                vm.ARTIST.postValue(vm.ARTIST.value)

            } else{
                Log.d("debug::","network is 0 ${it.toString()}")
                vm.flag1=0
                if(vm.flag1==0){
                    Toast.makeText(this,"no network", Toast.LENGTH_SHORT).show()
                }

            }
        }



        vm.Artist_info_LiveData.observe(this, Observer {
            binding.tvPlaycountArtist.text = it.artist.stats.playcount.toString()
            binding.tvFollowersArtist.text = it.artist.stats.listeners.toString()
            binding.tvDescArtist.text = it.artist.bio.summary.toString()
        })

        vm.Artist_info_LiveData.observe(this, Observer {
            Log.d("debug::","album information---------> ${it.toString()}")
            var array_genres = ArrayList<String>()
            for(item in it.artist.tags.tag){
                array_genres.add(item.name)
            }
            adapter_genre.setData(array_genres)

        })

        vm.Artist_top_tracks_live_data.observe(this, Observer {



            for(item in it.toptracks.track){
              list_my_tracks.add(TopTrack_a("not working",item.name,item.artist.name))
            }
            Log.d("debug::","testing in artist tracks size -- > ${list_my_tracks.size}")

            adapter_tracks.setData(list_my_tracks)

        //    binding.lvTopTracksInArtist.adapter = ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,arr_top_track)

        })

        vm.Artist_top_album_live_data.observe(this, Observer {


            for(item in it.topalbums.album){
                Log.d("debug::","testing in artist --> top tracks name = ${item.name} and artist = ${item.artist.name}")
                list_my_album.add(TopAlbums_a("not working",item.name,item.artist.name))
            }

            Log.d("debug::","testing in artist -- > ${list_my_album.size}")
            adapter_album.setData(list_my_album)

         //   binding.lvTopAlbumsInArtist.adapter = ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,arr_top_album)

        })

        Log.d("debug::"," artist---0-- ")




    }

    override fun onItemClick(position: Int, v: View?) {

        if(v !=null){
            var tv = v?.findViewById<TextView>(R.id.tv_genre_text) as TextView
            Log.d("debug:"," ${tv.text.toString()}")

            tv.background = getDrawable(R.drawable.after_click_genre)

            val intent = Intent(this@Artist_activity,Genre_one::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.putExtra("tag",tv.text.toString())
            startActivity(intent)

        }
    }

    override fun onItemClickx(position: Int, v: View?) {
        var intent = Intent(this,Album_activity::class.java)

            intent.putExtra("artist",vm.artistx)
            intent.putExtra("album",list_my_album.get(position).title)

            startActivity(intent)

            Log.d("debug::","clicked on top album inside artist ")
    }

    override fun onItemClicky(position: Int, v: View?) {

    }
}