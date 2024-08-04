package com.dicoding.tmdbclient.presentation.di.core

import com.dicoding.tmdbclient.data.repository.artist.ArtistRepositoryImpl
import com.dicoding.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.dicoding.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.dicoding.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.dicoding.tmdbclient.data.repository.movie.MovieRepositoryImpl
import com.dicoding.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.dicoding.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.dicoding.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.dicoding.tmdbclient.data.repository.tvshow.TvShowRepositoryImpl
import com.dicoding.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.dicoding.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.dicoding.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.dicoding.tmdbclient.domain.repository.ArtistRepository
import com.dicoding.tmdbclient.domain.repository.MoviesRepository
import com.dicoding.tmdbclient.domain.repository.TvShowsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource,
        movieCacheDataSource: MovieCacheDataSource
    ): MoviesRepository {
        return MovieRepositoryImpl(
            movieRemoteDataSource,
            movieLocalDataSource,
            movieCacheDataSource
        )
    }

    @Singleton
    @Provides
    fun provideTvShowRepository(
        tvShowRemoteDataSource: TvShowRemoteDataSource,
        tvShowLocalDataSource: TvShowLocalDataSource,
        tvShowCacheDataSource: TvShowCacheDataSource
    ): TvShowsRepository {
        return TvShowRepositoryImpl(
            tvShowRemoteDataSource,
            tvShowLocalDataSource,
            tvShowCacheDataSource
        )
    }

    @Singleton
    @Provides
    fun provideArtistRepository(
        artistRemoteDataSource: ArtistRemoteDataSource,
        artistLocalDataSource: ArtistLocalDataSource,
        artistCacheDataSource: ArtistCacheDataSource
    ): ArtistRepository {
        return ArtistRepositoryImpl(
            artistRemoteDataSource,
            artistLocalDataSource,
            artistCacheDataSource
        )
    }
}