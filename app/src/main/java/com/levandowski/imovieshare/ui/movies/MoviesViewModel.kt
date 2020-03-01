package com.levandowski.imovieshare.ui.movies

import androidx.paging.PagedList
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import com.levandowski.imovieshare.data.MovieDataSourceFactory
import androidx.lifecycle.ViewModel
import com.levandowski.imovieshare.data.MovieRepository
import com.levandowski.imovieshare.model.Movie
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.Executors.newFixedThreadPool
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val moviesRepository: MovieRepository
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val dataSourceFactory by lazy {
        MovieDataSourceFactory(moviesRepository, compositeDisposable)
    }

    val moviesPagedList: LiveData<PagedList<Movie>>
    val sourceLiveData = dataSourceFactory.sourceLiveData

    init {
        moviesPagedList = LivePagedListBuilder(dataSourceFactory, getConfigToPagedList())
            .setFetchExecutor(newFixedThreadPool(NUMBER_MAX_PROCESS))
            .build()
    }

    private fun getConfigToPagedList(): PagedList.Config = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(INITIAL_LOADING_PAGE_SIZE)
        .setPageSize(PAGE_SIZE)
        .setPrefetchDistance(MINIMUM_ORDER_SIZE)
        .build()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    companion object {
        private const val NUMBER_MAX_PROCESS = 5
        private const val INITIAL_LOADING_PAGE_SIZE = 10
        private const val PAGE_SIZE = 20
        private const val MINIMUM_ORDER_SIZE = 4
    }
}
