package com.dicoding.tmdbclient.presentation.di.core

import android.content.Context
import androidx.room.Room
import com.dicoding.tmdbclient.data.db.ArtistDao
import com.dicoding.tmdbclient.data.db.MovieDao
import com.dicoding.tmdbclient.data.db.TMDBDatabase
import com.dicoding.tmdbclient.data.db.TvShowDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): TMDBDatabase {
        return Room.databaseBuilder(context, TMDBDatabase::class.java, "tmdbclient")
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(tmdbDatabase: TMDBDatabase): MovieDao {
        return tmdbDatabase.MovieDao()
    }

    @Singleton
    @Provides
    fun provideTvShowDao(tmdbDatabase: TMDBDatabase): TvShowDao {
        return tmdbDatabase.TvShowDao()
    }

    @Singleton
    @Provides
    fun provideArtistDao(tmdbDatabase: TMDBDatabase): ArtistDao {
        return tmdbDatabase.ArtistDao()
    }
}