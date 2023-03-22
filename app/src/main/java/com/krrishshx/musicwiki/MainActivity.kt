package com.krrishshx.musicwiki

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
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
/////////////////////////////////! NETWORK HANDLER ////////////////////////////////////////////////////////////
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

/////////////////////////////////////////////////////////////////////////////////////////////






///////////////////////////////////!! VIEW MODEL//////////////////////////////////////////////////////////
        val genreService = RetrofitHelper.getInstance().create(GenreService::class.java)
        val repo = GenreRepo(genreService)
        genreViewModel = ViewModelProvider(this,GenreViewModelFactory(repo)).get(GenreViewModel::class.java)

   //========================================OBSERVERS============================================================================




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


        genreViewModel.click_on_expand_text.observe(this, Observer {
            binding.tvExpand.text = it.toString()
        })


        binding.tvExpand.setOnClickListener {

if(this::list.isInitialized) {
    Log.d("debug:", "click to expand")

    binding.ivExpand.animate().apply {
        duration=1000
        rotationXBy(180f)
    }



    var top_genre = ArrayList<String>()
if(genreViewModel.click_on_expand_text.value.equals("click to expand")){
    for (item in list) {
        top_genre.add(item.name.toString())
    }
    genreViewModel.DoOnExpandClick(1)
}else{
    var pp=10
    for (item in list) {
        top_genre.add(item.name.toString())
        pp--
        if(pp==0){
            break
        }
    }
    genreViewModel.DoOnExpandClick(0)

}
    adapter.setData(top_genre)
}
        }


        binding.constraintLayout.setOnClickListener {
            binding.imageView.animate().apply {
                duration = 1000
                rotationYBy(180f)
            }.start()
        }



    }

    override fun onItemClick(position: Int, v: View?) {


        if(v !=null){
            var tv = v?.findViewById<TextView>(R.id.tv_genre_text) as TextView
            Log.d("debug:"," ${tv.text.toString()}")
            val intent = Intent(this@MainActivity,Genre_one::class.java)
            intent.putExtra("tag",tv.text.toString())
            startActivity(intent)

        }



    }
}