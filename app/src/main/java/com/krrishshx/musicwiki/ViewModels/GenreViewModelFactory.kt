package com.krrishshx.musicwiki.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.krrishshx.musicwiki.repos.GenreRepo

class GenreViewModelFactory(private val repo: GenreRepo) :ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GenreViewModel(repo) as T
    }
}