package com.levandowski.imovieshare.data.remote.genre

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreDataInterface {

    @GET("genre/movie/list")
    fun getGenreMovieAsync(
        @Query("api_key") apiKey: String
    ): Single<GenreResponse>
}
