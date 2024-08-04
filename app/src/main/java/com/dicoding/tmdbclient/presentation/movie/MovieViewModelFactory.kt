package com.dicoding.tmdbclient.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.dicoding.tmdbclient.domain.usecase.ViewMoviesUseCase

class MovieViewModelFactory(
    private val viewMoviesUseCase: ViewMoviesUseCase,
    private val updateMoviesUseCase: UpdateMoviesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(viewMoviesUseCase, updateMoviesUseCase) as T
    }
}