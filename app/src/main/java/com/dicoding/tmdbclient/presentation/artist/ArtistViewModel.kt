package com.dicoding.tmdbclient.presentation.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dicoding.tmdbclient.domain.usecase.UpdateArtistsUseCase
import com.dicoding.tmdbclient.domain.usecase.ViewArtistsUseCase

class ArtistViewModel(
    private val viewArtistsUseCase: ViewArtistsUseCase,
    private val updateArtistsUseCase: UpdateArtistsUseCase
) : ViewModel() {
    fun getArtist() = liveData {
        val artistList = viewArtistsUseCase.execute()
        emit(artistList)
    }

    fun updateArtist() = liveData {
        val artistList = updateArtistsUseCase.execute()
        emit(artistList)
    }
}