package com.dicoding.tmdbclient.presentation.di.core

import com.dicoding.tmdbclient.domain.repository.ArtistRepository
import com.dicoding.tmdbclient.domain.repository.MoviesRepository
import com.dicoding.tmdbclient.domain.repository.TvShowsRepository
import com.dicoding.tmdbclient.domain.usecase.UpdateArtistsUseCase
import com.dicoding.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.dicoding.tmdbclient.domain.usecase.UpdateTvShowsUseCase
import com.dicoding.tmdbclient.domain.usecase.ViewArtistsUseCase
import com.dicoding.tmdbclient.domain.usecase.ViewMoviesUseCase
import com.dicoding.tmdbclient.domain.usecase.ViewTvShowsUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideViewMoviesUseCase(moviesRepository: MoviesRepository): ViewMoviesUseCase{
        return ViewMoviesUseCase(moviesRepository)
    }
    @Provides
    fun provideUpdateMoviesUseCase(moviesRepository: MoviesRepository): UpdateMoviesUseCase{
        return UpdateMoviesUseCase(moviesRepository)
    }
    @Provides
    fun provideViewTvShowsUseCase(tvShowsRepository: TvShowsRepository): ViewTvShowsUseCase{
        return ViewTvShowsUseCase(tvShowsRepository)
    }
    @Provides
    fun provideUpdateTvShowsUseCase(tvShowsRepository: TvShowsRepository): UpdateTvShowsUseCase{
        return UpdateTvShowsUseCase(tvShowsRepository)
    }
    @Provides
    fun provideViewArtistsUseCase(artistRepository: ArtistRepository): ViewArtistsUseCase{
        return ViewArtistsUseCase(artistRepository)
    }
    @Provides
    fun provideUpdateArtistsUseCase(artistRepository: ArtistRepository): UpdateArtistsUseCase{
        return UpdateArtistsUseCase(artistRepository)
    }
}