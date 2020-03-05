package com.levandowski.imovieshare.data.remote.movie

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDataInterface {

    @GET("movie/upcoming")
    fun getUpcomingMoviesAsync(
        @Query("api_key") apiKey: String,
        @Query("page") page: Long
    ): Single<MovieResponse>
}
