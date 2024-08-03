package com.dicoding.tmdbclient.data.repository.movie

import android.util.Log
import com.dicoding.tmdbclient.data.model.movie.Movie
import com.dicoding.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.dicoding.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.dicoding.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.dicoding.tmdbclient.domain.repository.MoviesRepository

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
) : MoviesRepository {
    override suspend fun getMovies(): List<Movie> = getMoviesFromCache()
    override suspend fun updateMovies(): List<Movie> {
        val newListOfMovies: List<Movie> = getMoviesFromAPI()
        movieLocalDataSource.clearAll()
        movieLocalDataSource.saveMoviesToDB(newListOfMovies)
        movieCacheDataSource.saveMoviesToCache(newListOfMovies)
        return newListOfMovies
    }

    private suspend fun getMoviesFromAPI(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            val response = movieRemoteDataSource.getMovies()
            val body = response.body()
            if (body != null) {
                movieList = body.movies
            }
        } catch (e: Exception) {
            Log.i("MYTAG", e.message.toString())
        }
        return movieList
    }

    private suspend fun getMoviesFromDB(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            movieList = movieLocalDataSource.getMoviesFromDB()
        } catch (e: Exception) {
            Log.i("MYTAG", e.message.toString())
        }
        if (movieList.isNotEmpty()) return movieList
        else {
            movieList = getMoviesFromAPI()
            movieLocalDataSource.saveMoviesToDB(movieList)
        }
        return movieList
    }

    private suspend fun getMoviesFromCache(): List<Movie>{
        lateinit var movieList: List<Movie>
        try {
            movieList = movieCacheDataSource.getMoviesFromCache()
        } catch (e: Exception) {
            Log.i("MYTAG", e.message.toString())
        }
        if (movieList.isNotEmpty()) return movieList
        else {
            movieList = getMoviesFromDB()
            movieCacheDataSource.saveMoviesToCache(movieList)
        }
        return movieList
    }
}