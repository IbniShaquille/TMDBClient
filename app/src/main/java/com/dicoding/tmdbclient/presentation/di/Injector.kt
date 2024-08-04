package com.dicoding.tmdbclient.presentation.di

import com.dicoding.tmdbclient.presentation.di.artist.ArtistSubComponent
import com.dicoding.tmdbclient.presentation.di.movie.MovieSubComponent
import com.dicoding.tmdbclient.presentation.di.tvshow.TvShowSubComponent

interface Injector {
    fun createMovieSubComponent():MovieSubComponent
    fun createArtistSubComponent():ArtistSubComponent
    fun createTvShowSubComponent():TvShowSubComponent
}