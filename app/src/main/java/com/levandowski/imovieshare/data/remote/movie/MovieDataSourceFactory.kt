package com.levandowski.imovieshare.data.remote.movie

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.levandowski.imovieshare.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDataSourceFactory @Inject constructor(
    private val movieDataInterface: MovieDataInterface
) : DataSource.Factory<Long, Movie>() {

    val sourceLiveData by lazy {
        MutableLiveData<MovieDataSource>()
    }

    override fun create(): DataSource<Long, Movie> {
        val source = MovieDataSource(
            movieDataInterface
        )
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                sourceLiveData.value = source
            }
        }
        return source
    }
}
