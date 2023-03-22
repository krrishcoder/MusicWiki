package com.krrishshx.musicwiki

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.krrishshx.musicwiki.ViewModels.Genre_one_ViewModel
import com.krrishshx.musicwiki.ViewModels.Genre_one_vmf
import com.krrishshx.musicwiki.adapters.ViewPagerMyAdapterOne
import com.krrishshx.musicwiki.api.GenreService
import com.krrishshx.musicwiki.api.RetrofitHelper
import com.krrishshx.musicwiki.databinding.ActivityGenreOneBinding
import com.krrishshx.musicwiki.modelx.Tag
import com.krrishshx.musicwiki.modelz.TopAlbums_a
import com.krrishshx.musicwiki.network.NetwokConnectivityObserver
import com.krrishshx.musicwiki.repos.GenreRepo

class Genre_one : AppCompatActivity() {

    lateinit var binding: ActivityGenreOneBinding
    lateinit var vm:Genre_one_ViewModel

    lateinit var conectionLiveData : NetwokConnectivityObserver



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityGenreOneBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )

        }

       val genreService = RetrofitHelper.getInstance().create(GenreService::class.java)
       val repo = GenreRepo(genreService)
       vm = ViewModelProvider(this,Genre_one_vmf(repo)).get(Genre_one_ViewModel::class.java)

        conectionLiveData = NetwokConnectivityObserver(this)

        vm.TAG.postValue( intent.getStringExtra("tag")!!)



        conectionLiveData.observe(this){
            if(it){


                Log.d("debug::","network is 1  ${it.toString()}")

                vm.netwok_status.postValue("1")
                vm.flag1=1
                vm.TAG.postValue(vm.TAG.value)

                vm.callToNetwork()

            } else{
                Log.d("debug::","network is 0 ${it.toString()}")

                vm.netwok_status.postValue("0")

                vm.flag1=0

                if(vm.flag1==0){
                    Toast.makeText(this,"no network", Toast.LENGTH_SHORT).show()
                }

            }
        }










        var vpadapter = ViewPagerMyAdapterOne(this,this,vm)
        binding.viewPager.adapter = vpadapter


        vm.TAG.observe(this, Observer {
            binding.tvGenreTitle.text = it.toString()
            if(vm.flag1==1){
                vm.getGenreInfo(it.toString())                         //netwok call
            }

        })


        vm.genre_Info.observe(this, Observer {
            binding.tvSummaryOfGenre.text = it.tag.wiki.summary.toString()
        })





        binding.tab.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab != null){
                    binding.viewPager.setCurrentItem(tab.position)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })


        binding.viewPager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tab.getTabAt(position)?.select()
            }

        })







        //1.) information about tag
        //https://ws.audioscrobbler.com/2.0/?method=tag.getinfo&tag=disco&api_key=09db9f968c52208fac594ffcfa47ae00&format=json

        // 2.) top albums for a tag
            //https://ws.audioscrobbler.com/2.0/? method=tag.gettopalbums &tag=disco &api_key=09db9f968c52208fac594ffcfa47ae00&format=json

        // 3.) top artist for a tag
        //https://ws.audioscrobbler.com/2.0/?method=tag.gettopartists&tag=disco &api_key=09db9f968c52208fac594ffcfa47ae00&format=json

        //4.) top tracks for a tag
        // https://ws.audioscrobbler.com/2.0/?method=tag.gettoptracks&tag=disco &api_key=09db9f968c52208fac594ffcfa47ae00&format=json
    }

    fun getVm(){


    }
}