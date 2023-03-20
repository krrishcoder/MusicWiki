package com.krrishshx.musicwiki.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krrishshx.musicwiki.models.TopGenreList
import com.krrishshx.musicwiki.repos.GenreRepo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class GenreViewModel(val repo: GenreRepo):ViewModel(){

   init{

      viewModelScope.launch(IO) {
          repo.getGenreList(1)          //telling repo to load data
      }

    }


    val genre :LiveData<TopGenreList>
        get() = repo.Genre               //getting data from repo


}
