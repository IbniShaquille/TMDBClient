package com.dicoding.tmdbclient.presentation

import android.app.Application
import com.dicoding.tmdbclient.BuildConfig
import com.dicoding.tmdbclient.presentation.di.Injector
import com.dicoding.tmdbclient.presentation.di.artist.ArtistSubComponent
import com.dicoding.tmdbclient.presentation.di.core.AppComponent
import com.dicoding.tmdbclient.presentation.di.core.AppModule
import com.dicoding.tmdbclient.presentation.di.core.DaggerAppComponent
import com.dicoding.tmdbclient.presentation.di.core.NetModule
import com.dicoding.tmdbclient.presentation.di.core.RemoteDataModule
import com.dicoding.tmdbclient.presentation.di.movie.MovieSubComponent
import com.dicoding.tmdbclient.presentation.di.tvshow.TvShowSubComponent

class App : Application(), Injector {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netModule(NetModule(BuildConfig.TMDB_BASE_URL))
            .remoteDataModule(RemoteDataModule(BuildConfig.TMDB_API_KEY))
            .build()
    }

    override fun createMovieSubComponent(): MovieSubComponent {
        return appComponent.movieSubComponent().create()
    }

    override fun createArtistSubComponent(): ArtistSubComponent {
        return appComponent.artistSubComponent().create()
    }

    override fun createTvShowSubComponent(): TvShowSubComponent {
        return appComponent.tvShowSubComponent().create()
    }
}