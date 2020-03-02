package com.levandowski.imovieshare.ui.movies

import androidx.paging.PagedList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.levandowski.imovieshare.data.remote.movie.MovieDataSource
import com.levandowski.imovieshare.data.repository.MovieRepository
import com.levandowski.imovieshare.model.Movie
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    moviesRepository: MovieRepository
) : ViewModel() {

    val moviesPagedList: LiveData<PagedList<Movie>> = moviesRepository.moviesPagedList

    val sourceLiveData: MutableLiveData<MovieDataSource> = moviesRepository.sourceLiveData
}
