package com.dicoding.tmdbclient.presentation.di.core

import android.content.Context
import com.dicoding.tmdbclient.presentation.di.artist.ArtistSubComponent
import com.dicoding.tmdbclient.presentation.di.movie.MovieSubComponent
import com.dicoding.tmdbclient.presentation.di.tvshow.TvShowScope
import com.dicoding.tmdbclient.presentation.di.tvshow.TvShowSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    subcomponents = [
        MovieSubComponent::class,
        ArtistSubComponent::class,
        TvShowSubComponent::class
    ]
)
class AppModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }
}