package com.levandowski.imovieshare.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.levandowski.imovieshare.data.remote.genre.GenreDataSource
import com.levandowski.imovieshare.data.remote.genre.GenreResponse
import javax.inject.Inject

class GenreRepository @Inject constructor(
    private val genreDataSource: GenreDataSource
) {

    private val genreResponse by lazy {
        MutableLiveData<GenreResponse>()
    }

    fun getMovieGenres(): MutableLiveData<GenreResponse> {
        genreDataSource.requestGenreMoviesAsync(
            success = {
                genreResponse.value = it
            },
            failed = {
                Log.d(GenreRepository::class.java.name, it.message)
            }
        )
        return genreResponse
    }
}
