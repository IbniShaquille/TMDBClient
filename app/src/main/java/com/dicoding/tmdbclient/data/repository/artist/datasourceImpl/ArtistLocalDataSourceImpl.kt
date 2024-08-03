package com.dicoding.tmdbclient.data.repository.artist.datasourceImpl

import com.dicoding.tmdbclient.data.db.ArtistDao
import com.dicoding.tmdbclient.data.model.artist.Artist
import com.dicoding.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistLocalDataSourceImpl (private val artistDao: ArtistDao): ArtistLocalDataSource {
    override suspend fun getArtistFromDB(): List<Artist> = artistDao.getAllArtists()

    override suspend fun saveArtistToDB(artists: List<Artist>) {
        CoroutineScope(Dispatchers.IO).launch {
            artistDao.saveArtists(artists)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            artistDao.deleteArtists()
        }
    }
}