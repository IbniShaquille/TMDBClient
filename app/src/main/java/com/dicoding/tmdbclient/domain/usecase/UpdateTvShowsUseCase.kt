package com.dicoding.tmdbclient.domain.usecase

import com.dicoding.tmdbclient.data.model.tvshow.TvShow
import com.dicoding.tmdbclient.domain.repository.TvShowsRepository

class UpdateTvShowsUseCase(private val tvShowsRepository: TvShowsRepository) {
    suspend fun execute(): List<TvShow>? = tvShowsRepository.updateTvShows()
}