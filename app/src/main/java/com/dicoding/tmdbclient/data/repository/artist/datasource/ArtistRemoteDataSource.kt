package com.dicoding.tmdbclient.data.repository.artist.datasource

import com.dicoding.tmdbclient.data.model.artist.ArtistList
import retrofit2.Response

interface ArtistRemoteDataSource {
    suspend fun getArtist(): Response<ArtistList>
}