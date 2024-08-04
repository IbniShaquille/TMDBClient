package com.dicoding.tmdbclient.presentation.di.movie

import com.dicoding.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.dicoding.tmdbclient.domain.usecase.ViewMoviesUseCase
import com.dicoding.tmdbclient.presentation.movie.MovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {
    @MovieScope
    @Provides
    fun provideMovieViewModelFactory(
        viewMoviesUseCase: ViewMoviesUseCase,
        updateMoviesUseCase: UpdateMoviesUseCase
    ): MovieViewModelFactory {
        return MovieViewModelFactory(
            viewMoviesUseCase,
            updateMoviesUseCase
        )
    }
}