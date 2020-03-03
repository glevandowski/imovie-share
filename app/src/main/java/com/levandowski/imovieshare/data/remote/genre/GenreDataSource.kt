package com.levandowski.imovieshare.data.remote.genre

import com.levandowski.imovieshare.BuildConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GenreDataSource @Inject constructor(
    private val genreDataInterface: GenreDataInterface
) {

    fun requestGenreMoviesAsync(
        success: (GenreResponse) -> Unit,
        failed: (Throwable) -> Unit
    ) {
        genreDataInterface.getGenreMovieAsync(
            BuildConfig.API_KEY
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                success.invoke(response)
            }, { throwable ->
                failed.invoke(throwable)
            })
    }
}
