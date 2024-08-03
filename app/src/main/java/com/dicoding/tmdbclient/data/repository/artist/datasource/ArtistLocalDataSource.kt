package com.dicoding.tmdbclient.data.repository.artist.datasource

import androidx.room.Delete
import com.dicoding.tmdbclient.data.model.artist.Artist

interface ArtistLocalDataSource {
    suspend fun getArtistFromDB(): List<Artist>
    suspend fun saveArtistToDB(artists: List<Artist>)
    suspend fun clearAll()
}