package com.dicoding.tmdbclient.data.repository.tvshow.datasourceImpl

import com.dicoding.tmdbclient.data.db.TvShowDao
import com.dicoding.tmdbclient.data.model.tvshow.TvShow
import com.dicoding.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvShowLocalDataSourceImpl (private val tvShowsDao: TvShowDao): TvShowLocalDataSource {
    override suspend fun getTvShowsFromDB(): List<TvShow> = tvShowsDao.getAllTvShows()
    override suspend fun saveTvShowsToDB(tvShows: List<TvShow>) {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowsDao.saveTvShows(tvShows)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowsDao.deleteTvShows()
        }
    }
}