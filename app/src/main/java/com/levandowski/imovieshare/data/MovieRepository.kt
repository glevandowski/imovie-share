package com.levandowski.imovieshare.data

import com.levandowski.imovieshare.data.remote.MovieResponse
import com.levandowski.imovieshare.data.remote.MovieDataInterface
import io.reactivex.Single
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDataInterface: MovieDataInterface
) {

    fun getUpcomingMoviesAsync(
        apiKey: String,
        numberPage: Long
    ): Single<MovieResponse>? {
        return movieDataInterface.getUpcomingMoviesAsync(apiKey, numberPage)
    }
}
