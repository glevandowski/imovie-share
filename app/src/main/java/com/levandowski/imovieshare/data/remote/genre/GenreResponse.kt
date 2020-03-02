package com.levandowski.imovieshare.data.remote.genre

import com.google.gson.annotations.SerializedName
import com.levandowski.imovieshare.model.Genre

data class GenreResponse (
    @SerializedName("genres")
    val genres: List<Genre>? = null
)
