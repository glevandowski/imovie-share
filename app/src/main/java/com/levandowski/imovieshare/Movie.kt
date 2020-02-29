package com.levandowski.imovieshare

data class Movie(
    val popularity: Double? = null,
    val voteCount: Int? = null,
    val video: Boolean? = null,
    val posterPath: String? = null,
    val id: Int? = null,
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val genreIds: List<Int>? = null,
    val title: String? = null,
    val voteAverage: Double? = null,
    val overview: String? = null,
    val releaseDate: String? = null
)
