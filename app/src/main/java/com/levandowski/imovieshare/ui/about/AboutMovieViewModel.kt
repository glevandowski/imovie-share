package com.levandowski.imovieshare.ui.about

import androidx.lifecycle.ViewModel
import com.levandowski.imovieshare.data.repository.GenreRepository
import javax.inject.Inject

class AboutMovieViewModel @Inject constructor(
    genreRepository: GenreRepository
) : ViewModel() {

    val movieGenres = genreRepository.getMovieGenres()
}
