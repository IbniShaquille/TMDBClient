package com.dicoding.tmdbclient.data.repository.artist.datasource

import com.dicoding.tmdbclient.data.model.artist.Artist

interface ArtistCacheDataSource {
    suspend fun getArtistFromCache(): List<Artist>
    suspend fun saveArtistToCache(artists: List<Artist>)
}