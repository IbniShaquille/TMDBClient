package com.dicoding.tmdbclient.data.repository.tvshow

import android.util.Log
import com.dicoding.tmdbclient.data.model.tvshow.TvShow
import com.dicoding.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.dicoding.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.dicoding.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.dicoding.tmdbclient.domain.repository.TvShowsRepository

class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowCacheDataSource: TvShowCacheDataSource
) : TvShowsRepository {
    override suspend fun getTvShows(): List<TvShow> = getTvShowsFromCache()

    override suspend fun updateTvShows(): List<TvShow> {
        val newListOfTvShow: List<TvShow> = getTvShowsFromAPI()
        tvShowLocalDataSource.clearAll()
        tvShowLocalDataSource.saveTvShowsToDB(newListOfTvShow)
        tvShowCacheDataSource.saveTvShowsToCache(newListOfTvShow)
        return newListOfTvShow
    }

    private suspend fun getTvShowsFromAPI(): List<TvShow> {
        lateinit var tvShows: List<TvShow>
        try {
            val response = tvShowRemoteDataSource.getTvShows()
            val body = response.body()
            if (body != null) {
                tvShows = body.tvShows
            }
        } catch (e: Exception) {
            Log.i("MYTAG", e.message.toString())
        }
        return tvShows
    }

    private suspend fun getTvShowsFromDB(): List<TvShow> {
        lateinit var tvShows: List<TvShow>
        try {
            tvShows = tvShowLocalDataSource.getTvShowsFromDB()
        } catch (e: Exception) {
            Log.i("MYTAG", e.message.toString())
        }
        if (tvShows.isNotEmpty()) return tvShows
        else {
            tvShows = getTvShowsFromAPI()
            tvShowLocalDataSource.saveTvShowsToDB(tvShows)
        }
        return tvShows
    }

    private suspend fun getTvShowsFromCache(): List<TvShow> {
        lateinit var tvShows: List<TvShow>
        try {
            tvShows = tvShowCacheDataSource.getTvShowsFromCache()
        } catch (e: Exception) {
            Log.i("MYTAG", e.message.toString())
        }
        if (tvShows.isNotEmpty()) return tvShows
        else {
            tvShows = getTvShowsFromDB()
            tvShowCacheDataSource.saveTvShowsToCache(tvShows)
        }
        return tvShows
    }
}