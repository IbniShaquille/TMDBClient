package com.dicoding.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.tmdbclient.data.model.artist.Artist

@Dao
interface ArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArtists(artists: List<Artist>)

    @Query("DELETE FROM popular_artist")
    suspend fun deleteArtists()

    @Query("SELECT * FROM popular_artist")
    suspend fun getAllArtists(): List<Artist>
}