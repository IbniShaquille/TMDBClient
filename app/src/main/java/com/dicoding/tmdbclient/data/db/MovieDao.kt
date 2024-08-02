package com.dicoding.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.tmdbclient.data.model.movie.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<Movie>)

    @Query("DELETE FROM popular_movie")
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM popular_movie")
    suspend fun getAllMovies() : List<Movie>
}