package com.levandowski.imovieshare.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.levandowski.imovieshare.data.remote.movie.MovieDataSource
import com.levandowski.imovieshare.data.remote.movie.MovieDataSourceFactory
import com.levandowski.imovieshare.model.Movie
import com.levandowski.imovieshare.ui.movies.ConfigPagedList
import java.util.concurrent.Executors.newFixedThreadPool
import javax.inject.Inject

class MovieRepository @Inject constructor(
    dataSourceFactory: MovieDataSourceFactory
) {

    val moviesPagedList: LiveData<PagedList<Movie>> =
        LivePagedListBuilder(dataSourceFactory, ConfigPagedList.getConfigToPagedList())
            .setFetchExecutor(newFixedThreadPool(NUMBER_MAX_PROCESS))
            .build()

    val sourceLiveData: MutableLiveData<MovieDataSource> = dataSourceFactory.sourceLiveData

    companion object {
        private const val NUMBER_MAX_PROCESS = 5
    }
}
