package com.krrishshx.musicwiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.krrishshx.musicwiki.ViewModels.GenreViewModel
import com.krrishshx.musicwiki.ViewModels.GenreViewModelFactory
import com.krrishshx.musicwiki.api.GenreService
import com.krrishshx.musicwiki.api.RetrofitHelper
import com.krrishshx.musicwiki.repos.GenreRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var genreViewModel: GenreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //from here we will access to view models

        val genreService = RetrofitHelper.getInstance().create(GenreService::class.java)

        val repo = GenreRepo(genreService)

        genreViewModel = ViewModelProvider(this,GenreViewModelFactory(repo)).get(GenreViewModel::class.java)



        genreViewModel.genre.observe(this, Observer {
            Log.d("debug:",it.tags.toString())
        })

    }
}