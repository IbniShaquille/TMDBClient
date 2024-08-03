package com.dicoding.tmdbclient.domain.repository

import com.dicoding.tmdbclient.data.model.tvshow.TvShow

interface TvShowsRepository {
    suspend fun getTvShows():List<TvShow>?
    suspend fun updateTvShows():List<TvShow>?
}