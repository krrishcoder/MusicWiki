package com.krrishshx.musicwiki.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.krrishshx.musicwiki.repos.GenreRepo

class Album_vmf (private val repo: GenreRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumViewModel(repo) as T
    }
}