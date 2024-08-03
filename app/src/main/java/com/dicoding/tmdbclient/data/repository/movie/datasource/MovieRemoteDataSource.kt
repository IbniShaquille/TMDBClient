package com.dicoding.tmdbclient.data.repository.movie.datasource

import com.dicoding.tmdbclient.data.model.movie.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getMovies(): Response<MovieList>
}