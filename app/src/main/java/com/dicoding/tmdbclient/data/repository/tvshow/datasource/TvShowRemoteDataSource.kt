package com.dicoding.tmdbclient.data.repository.tvshow.datasource

import com.dicoding.tmdbclient.data.model.tvshow.TvShowList
import retrofit2.Response

interface TvShowRemoteDataSource {
    suspend fun getTvShows(): Response<TvShowList>
}