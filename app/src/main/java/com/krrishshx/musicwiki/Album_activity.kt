package com.krrishshx.musicwiki

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.krrishshx.musicwiki.ViewModels.AlbumViewModel
import com.krrishshx.musicwiki.ViewModels.Album_vmf
import com.krrishshx.musicwiki.ViewModels.Genre_one_ViewModel
import com.krrishshx.musicwiki.ViewModels.Genre_one_vmf
import com.krrishshx.musicwiki.adapters.rv_adapter_genres
import com.krrishshx.musicwiki.api.GenreService
import com.krrishshx.musicwiki.api.RetrofitHelper
import com.krrishshx.musicwiki.databinding.ActivityAlbumBinding
import com.krrishshx.musicwiki.network.NetwokConnectivityObserver
import com.krrishshx.musicwiki.repos.GenreRepo
import com.squareup.picasso.Picasso

class Album_activity : AppCompatActivity(), rv_adapter_genres.OnItemClickListener  {
    lateinit var binding:ActivityAlbumBinding
    lateinit var vm:AlbumViewModel
    lateinit var conectionLiveData : NetwokConnectivityObserver

    private val adapter_genre by lazy { rv_adapter_genres(this,this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding  = DataBindingUtil.setContentView(this,R.layout.activity_album)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )

        }

        val genreService = RetrofitHelper.getInstance().create(GenreService::class.java)
        val repo = GenreRepo(genreService)
        vm = ViewModelProvider(this, Album_vmf(repo)).get(AlbumViewModel::class.java)

        //http://ws.audioscrobbler.com/2.0/?method=album.getinfo&api_key=09db9f968c52208fac594ffcfa47ae00&artist=Cher&album=Believe

        //=====================================================================!!!!================================================================================================
        conectionLiveData = NetwokConnectivityObserver(this)


        vm.NAME.postValue(intent.getStringExtra("album")!!)
        vm.ARTIST.postValue(intent.getStringExtra("artist")!!)
        vm.IMG.postValue(intent.getStringExtra("img")!!)

        binding.rvGenresAlbumActivity.adapter = adapter_genre
        binding.rvGenresAlbumActivity.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)


        conectionLiveData.observe(this){
            if(it){
                Log.d("debug::","network is 1  ${it.toString()}")
                vm.flag1=1
              //  vm.TAG.postValue(vm.TAG.value)
                vm.NAME.postValue(vm.NAME.value)

            } else{
                Log.d("debug::","network is 0 ${it.toString()}")
                vm.flag1=0
                if(vm.flag1==0){
                    Toast.makeText(this,"no network", Toast.LENGTH_SHORT).show()
                }

            }
        }






        vm.NAME.observe(this, Observer {
            if(vm.flag1==1) {
                vm.getAlbumInfo(it.toString(), intent.getStringExtra("artist")!!)
                vm.getAlbumArtistInfo(intent.getStringExtra("artist")!!)
            }
            binding.tvTitleAlbumActivity.text = it.toString()

            Picasso.get().load(vm.IMG.value).placeholder(R.drawable.music).into(binding.ivAlbumActivity)

        })

        vm.Album_Info.observe(this, Observer {
            Log.d("debug::","album information---------> ${it.toString()}")


            var array_genres = ArrayList<String>()

          for(item in it.album.tags.tag){
              array_genres.add(item.name)
          }

            adapter_genre.setData(array_genres)

        })

        vm.Album_Artist_info_LiveData.observe(this, Observer {
            Log.d("debug::","album  artist information---------> ${it.toString()}")
            binding.tvArtistInfoAlbumActivity.text = vm.removeLinks(it.artist.bio.summary.toString())
        })


    }

    override fun onItemClick(position: Int, v: View?) {

        if(v !=null){
            var tv = v?.findViewById<TextView>(R.id.tv_genre_text) as TextView
            Log.d("debug:"," ${tv.text.toString()}")

            tv.background = getDrawable(R.drawable.after_click_genre)

            val intent = Intent(this@Album_activity,Genre_one::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.putExtra("tag",tv.text.toString())
            startActivity(intent)

        }

    }
}