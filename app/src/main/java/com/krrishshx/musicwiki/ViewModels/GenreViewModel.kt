package com.krrishshx.musicwiki.ViewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krrishshx.musicwiki.modelx.topGenre
import com.krrishshx.musicwiki.network.NetwokConnectivityObserver


import com.krrishshx.musicwiki.repos.GenreRepo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class GenreViewModel(val repo: GenreRepo):ViewModel(){


   fun callToNetwok(){

      viewModelScope.launch(IO) {
          repo.getGenreList(1)          //telling repo to load data
      }

    }

   fun test(mContext:Context){
       var cm = NetwokConnectivityObserver(mContext)


   }



    val genre :LiveData<topGenre>
        get() = repo.Genre               //getting data from repo


}
