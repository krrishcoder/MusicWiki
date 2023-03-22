package com.krrishshx.musicwiki.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.krrishshx.musicwiki.repos.GenreRepo

class Artist_vmf(private val repo: GenreRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ArtistViewModel(repo) as T
    }
}