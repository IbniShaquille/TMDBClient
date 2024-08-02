package com.dicoding.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.tmdbclient.data.model.tvshow.TvShow

@Dao
interface TvShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTvShows(tvShows: List<TvShow>)

    @Query("DELETE FROM popular_tv_show")
    suspend fun deleteTvShows()

    @Query("SELECT * FROM popular_tv_show")
    suspend fun getAllTvShows() : List<TvShow>

}