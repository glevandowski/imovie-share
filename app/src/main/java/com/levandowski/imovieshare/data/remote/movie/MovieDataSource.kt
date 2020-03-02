package com.levandowski.imovieshare.data.remote.movie

import androidx.paging.PageKeyedDataSource
import com.levandowski.imovieshare.BuildConfig
import com.levandowski.imovieshare.model.Movie
import com.levandowski.imovieshare.data.remote.NetworkState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MovieDataSource(
    private val movieDataInterface: MovieDataInterface
) : PageKeyedDataSource<Long, Movie>() {

    private val paginatedNetworkStateLiveData by lazy {
        MutableLiveData<NetworkState>()
    }

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Movie>
    ) {
        requestUpcomingMovies(initialPosition = 1) {
            callback.onResult(it.movies?.toList() ?: listOf(), null, 2)
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Movie>) {}

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Movie>) {
        requestUpcomingMovies(initialPosition = params.key) {
            callback.onResult(it.movies?.toList() ?: listOf(), params.key + 1)
        }
    }

    private fun requestUpcomingMovies(
        initialPosition: Long,
        responseMethod: (MovieResponse) -> Unit
    ) {
        paginatedNetworkStateLiveData.postValue(NetworkState.LOADING)
        val request = movieDataInterface.getUpcomingMoviesAsync(
            BuildConfig.API_KEY,
            initialPosition
        ).subscribe({ response ->
            paginatedNetworkStateLiveData.postValue(NetworkState.LOADED)
            responseMethod.invoke(response)
        }, { throwable ->
            paginatedNetworkStateLiveData.postValue(NetworkState.error(throwable.message))
        })

        when (request) {
            null -> paginatedNetworkStateLiveData.postValue(NetworkState.error(""))
        }
    }

    fun getPaginatedNetworkStateLiveData(): LiveData<NetworkState> {
        return paginatedNetworkStateLiveData
    }
}
