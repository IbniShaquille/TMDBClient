package com.dicoding.tmdbclient.presentation.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.tmdbclient.domain.usecase.UpdateArtistsUseCase
import com.dicoding.tmdbclient.domain.usecase.ViewArtistsUseCase

class ArtistViewModelFactory(
    private val viewArtistsUseCase: ViewArtistsUseCase,
    private val updateArtistsUseCase: UpdateArtistsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ArtistViewModel(viewArtistsUseCase, updateArtistsUseCase) as T
    }
}