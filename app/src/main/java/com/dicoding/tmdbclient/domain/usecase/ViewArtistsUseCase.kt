package com.dicoding.tmdbclient.domain.usecase

import com.dicoding.tmdbclient.data.model.artist.Artist
import com.dicoding.tmdbclient.domain.repository.ArtistRepository

class ViewArtistsUseCase(private val artistRepository: ArtistRepository) {
    suspend fun execute(): List<Artist>? = artistRepository.getArtists()
}