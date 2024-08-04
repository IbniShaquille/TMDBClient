package com.dicoding.tmdbclient.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dicoding.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.dicoding.tmdbclient.domain.usecase.ViewMoviesUseCase

class MovieViewModel(
    private val viewMoviesUseCase: ViewMoviesUseCase,
    private val updateMoviesUseCase: UpdateMoviesUseCase
) : ViewModel() {
    fun getMovies() = liveData {
        val movieList = viewMoviesUseCase.execute()
        emit(movieList)
    }
    fun updateMovies() = liveData {
        val movieList = updateMoviesUseCase.execute()
        emit(movieList)
    }
}