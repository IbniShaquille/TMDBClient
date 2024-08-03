package com.dicoding.tmdbclient.domain.repository

import com.dicoding.tmdbclient.data.model.artist.Artist

interface ArtistRepository {
    suspend fun getArtists(): List<Artist>?
    suspend fun updateArtists():List<Artist>?
}