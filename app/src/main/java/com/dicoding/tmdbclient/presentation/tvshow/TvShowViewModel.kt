package com.dicoding.tmdbclient.presentation.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dicoding.tmdbclient.domain.usecase.UpdateTvShowsUseCase
import com.dicoding.tmdbclient.domain.usecase.ViewTvShowsUseCase

class TvShowViewModel(
    private val viewTvShowsUseCase: ViewTvShowsUseCase,
    private val updateTvShowsUseCase: UpdateTvShowsUseCase
) : ViewModel() {
    fun getTvShows() = liveData {
        val tvShowList = viewTvShowsUseCase.execute()
        emit(tvShowList)
    }

    fun updateTvShows() = liveData {
        val tvShowList = updateTvShowsUseCase.execute()
        emit(tvShowList)
    }
}