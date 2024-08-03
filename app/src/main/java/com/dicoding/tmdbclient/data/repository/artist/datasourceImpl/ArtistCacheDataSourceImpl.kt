package com.dicoding.tmdbclient.data.repository.artist.datasourceImpl

import com.dicoding.tmdbclient.data.model.artist.Artist
import com.dicoding.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource

class ArtistCacheDataSourceImpl : ArtistCacheDataSource {
    private var artistList = ArrayList<Artist>()
    override suspend fun getArtistFromCache(): List<Artist> = artistList

    override suspend fun saveArtistToCache(artists: List<Artist>) {
        artistList.clear()
        artistList = ArrayList(artists)
    }
}