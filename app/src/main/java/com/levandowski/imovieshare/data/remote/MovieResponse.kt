package com.levandowski.imovieshare.data.remote

import com.google.gson.annotations.SerializedName
import com.levandowski.imovieshare.model.Movie

data class MovieResponse(
    @SerializedName("page")
    var page: Int? = null,

    @SerializedName("total_results")
    var totalMovies: Int? = null,

    @SerializedName("total_pages")
    var totalPages: Int? = null,

    @SerializedName("results")
    var movies: List<Movie>? = null
)
