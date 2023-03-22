package com.krrishshx.musicwiki

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.krrishshx.musicwiki.ViewModels.GenreViewModel
import com.krrishshx.musicwiki.ViewModels.GenreViewModelFactory
import com.krrishshx.musicwiki.adapters.rv_adapter_genres
import com.krrishshx.musicwiki.api.GenreService
import com.krrishshx.musicwiki.api.RetrofitHelper
import com.krrishshx.musicwiki.databinding.ActivityMainBinding
import com.krrishshx.musicwiki.modelx.Tag
import com.krrishshx.musicwiki.network.NetwokConnectivityObserver


import com.krrishshx.musicwiki.repos.GenreRepo


class MainActivity : AppCompatActivity(), rv_adapter_genres.OnItemClickListener {

    lateinit var genreViewModel: GenreViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var adapter_geners:rv_adapter_genres
    lateinit var list:ArrayList<Tag>
    lateinit var conectionLiveData : NetwokConnectivityObserver

     var flag1 = 0

    private val adapter by lazy { rv_adapter_genres(this,this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_main)







        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )

        }
        //from here we will access to view models

        conectionLiveData = NetwokConnectivityObserver(this)

        conectionLiveData.observe(this){
            if(it){

                Log.d("debug::","network is 1  ${it.toString()}")
                flag1=1
               genreViewModel.callToNetwok()

            } else{
                Log.d("debug::","network is 0 ${it.toString()}")
                if(flag1==0){
                    Toast.makeText(this,"no network",Toast.LENGTH_SHORT).show()
                }

            }
        }





//
//        genreViewModel.isNetworkAvail.observe(this, Observer {
//            Log.d("debug::","network is ${it.toString()}")
//        })













        val genreService = RetrofitHelper.getInstance().create(GenreService::class.java)

        val repo = GenreRepo(genreService)
//
        genreViewModel = ViewModelProvider(this,GenreViewModelFactory(repo)).get(GenreViewModel::class.java)
//
        binding.rvGenres.adapter = adapter
        binding.rvGenres.layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)


        genreViewModel.genre.observe(this, Observer {
            Log.d("debug:",it.toptags.tag.toString())
            list= it.toptags.tag as ArrayList<Tag>
            var top_genre_list = ArrayList<String>()

            var counter =0
           for(item in list){

              top_genre_list.add(item.name.toString())
               counter++
               if(counter==10){
                   break
               }

           }

            adapter.setData(top_genre_list)




        })



        binding.tvExpand.setOnClickListener {

            Log.d("debug:","click to expand")
            var top_genre = ArrayList<String>()


            for(item in list) {
                top_genre.add(item.name.toString())
            }

            adapter.setData(top_genre)
        }



    }

    override fun onItemClick(position: Int, v: View?) {


        if(v !=null){
            var tv = v?.findViewById<TextView>(R.id.tv_genre_text) as TextView
            Log.d("debug:"," ${tv.text.toString()}")

            tv.background = getDrawable(R.drawable.after_click_genre)

            val intent = Intent(this@MainActivity,Genre_one::class.java)
            intent.putExtra("tag",tv.text.toString())
            startActivity(intent)

        }



    }
}