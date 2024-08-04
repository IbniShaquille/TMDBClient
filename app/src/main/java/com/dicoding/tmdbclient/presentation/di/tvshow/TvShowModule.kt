package com.dicoding.tmdbclient.presentation.di.tvshow

import com.dicoding.tmdbclient.domain.usecase.UpdateTvShowsUseCase
import com.dicoding.tmdbclient.domain.usecase.ViewTvShowsUseCase
import com.dicoding.tmdbclient.presentation.tvshow.TvShowViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TvShowModule {
    @TvShowScope
    @Provides
    fun provideTvShowViewModelFactory(
        viewTvShowsUseCase: ViewTvShowsUseCase,
        updateTvShowsUseCase: UpdateTvShowsUseCase
    ): TvShowViewModelFactory {
        return TvShowViewModelFactory(
            viewTvShowsUseCase,
            updateTvShowsUseCase
        )
    }
}