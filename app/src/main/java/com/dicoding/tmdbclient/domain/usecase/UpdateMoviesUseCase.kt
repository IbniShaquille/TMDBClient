package com.dicoding.tmdbclient.domain.usecase

import com.dicoding.tmdbclient.data.model.movie.Movie
import com.dicoding.tmdbclient.domain.repository.MoviesRepository

class UpdateMoviesUseCase(private val moviesRepository: MoviesRepository) {
    suspend fun execute(): List<Movie>? = moviesRepository.updateMovies()
}