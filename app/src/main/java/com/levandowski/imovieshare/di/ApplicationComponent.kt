package com.levandowski.imovieshare.di

import com.levandowski.imovieshare.ui.about.AboutMovieFragment
import com.levandowski.imovieshare.ui.movies.MoviesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(moviesFragment: MoviesFragment)
    fun inject(aboutMovieFragment: AboutMovieFragment)
}
