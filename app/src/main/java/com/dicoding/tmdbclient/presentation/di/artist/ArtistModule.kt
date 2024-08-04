package com.dicoding.tmdbclient.presentation.di.artist

import com.dicoding.tmdbclient.domain.usecase.UpdateArtistsUseCase
import com.dicoding.tmdbclient.domain.usecase.ViewArtistsUseCase
import com.dicoding.tmdbclient.presentation.artist.ArtistViewModel
import com.dicoding.tmdbclient.presentation.artist.ArtistViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ArtistModule {
    @ArtistScope
    @Provides
    fun provideArtistViewModelFactory(
        viewArtistsUseCase: ViewArtistsUseCase,
        updateArtistsUseCase: UpdateArtistsUseCase
    ): ArtistViewModelFactory {
        return ArtistViewModelFactory(
            viewArtistsUseCase,
            updateArtistsUseCase
        )
    }
}