package com.dicoding.tmdbclient.domain.repository

import com.dicoding.tmdbclient.data.model.movie.Movie

interface MoviesRepository {
    suspend fun getMovies(): List<Movie>?
    suspend fun updateMovies(): List<Movie>?
}