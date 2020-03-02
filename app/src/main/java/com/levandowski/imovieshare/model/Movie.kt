package com.levandowski.imovieshare.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.*
import com.google.gson.annotations.SerializedName
import com.levandowski.imovieshare.util.GsonConverter

data class Movie(
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null,
    @SerializedName("video")
    val video: Boolean? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("genre_ids")
    val genreIds: List<Int>? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null
): GsonConverter {

    override fun unwrapElement(json: String): Movie? {
        return Gson().fromJson(json, Movie::class.java)
    }

    override fun wrap(): String {
        return Gson().toJson(this)
    }

    private fun getFilteredGenres(genres: List<Genre>) =
        genres.filter { genre -> genreIds?.any { it == genre.id } ?: false }

    fun getCategory(genres: List<Genre>): String {
        var category = ""
        getFilteredGenres(genres).forEach { genre ->
            category += "${genre.name} \n"
        }
        return category
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Movie> = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}
