package com.levandowski.imovieshare.ui.about

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.levandowski.imovieshare.R
import com.levandowski.imovieshare.di.DaggerApplicationComponent
import com.levandowski.imovieshare.di.ViewModelFactory
import com.levandowski.imovieshare.model.Movie
import kotlinx.android.synthetic.main.about_movie_fragment.*
import javax.inject.Inject

class AboutMovieFragment : Fragment() {

    @Inject
    lateinit var viewModeFactory: ViewModelFactory

    private val viewModel: AboutMovieViewModel
        get() = ViewModelProviders.of(this, viewModeFactory).get(AboutMovieViewModel::class.java)

    init {
        DaggerApplicationComponent.builder().build().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.about_movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) { super.onActivityCreated(savedInstanceState)
        val movie = arguments?.let { Gson().fromJson(AboutMovieFragmentArgs.fromBundle(it).movie, Movie::class.java) }

        tv_about_movie_title.text = movie?.title
        rb_vote_average_about_movie.rating = movie?.voteAverage?.toFloat() ?: 0.0F
        tv_overview_about_movie.text = movie?.overview
    }
}
