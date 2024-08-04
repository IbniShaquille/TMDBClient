package com.dicoding.tmdbclient.presentation.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.tmdbclient.domain.usecase.UpdateTvShowsUseCase
import com.dicoding.tmdbclient.domain.usecase.ViewTvShowsUseCase

class TvShowViewModelFactory(
    private val viewTvShowsUseCase: ViewTvShowsUseCase,
    private val updateTvShowsUseCase: UpdateTvShowsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TvShowViewModel(viewTvShowsUseCase, updateTvShowsUseCase) as T
    }
}