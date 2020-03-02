package com.levandowski.imovieshare.ui.about

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.levandowski.imovieshare.R
import com.levandowski.imovieshare.data.remote.genre.GenreResponse
import com.levandowski.imovieshare.di.DaggerApplicationComponent
import com.levandowski.imovieshare.di.ViewModelFactory
import com.levandowski.imovieshare.model.Movie
import com.levandowski.imovieshare.ui.MainActivity
import com.levandowski.imovieshare.util.Urls
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.about_movie_fragment.*
import kotlinx.android.synthetic.main.body_about_movie.*
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
        when (activity) {
            is MainActivity -> {
                (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
                (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
            }
        }
        return inflater.inflate(R.layout.about_movie_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()
        val movie = getMovieBundle()
        movie?.let {
            setupFieldsToLayout(movie = it)
        }

        viewModel.movieGenres.observe(this, Observer<GenreResponse> {
            tv_category_about_movie.text = it.genres?.let { genres -> movie?.getCategory(genres) }
        })
    }

    private fun setupFieldsToLayout(movie: Movie) {
        Picasso.get()
            .load(Urls.BASE_URL_IMAGE_SIZE_ORIGINAL + movie.posterPath)
            .fit()
            .centerCrop()
            .into(iv_about_movie)

        tv_about_movie_title.text = movie.title
        tv_vote_average.text = movie.voteAverage?.toString() ?: ""
        tv_overview_about_movie.text = movie.overview
        tv_release_date_about_movie.text = movie.releaseDate
    }

    private fun getMovieBundle() = arguments?.let {
        Movie().unwrapElement(AboutMovieFragmentArgs.fromBundle(it).movie)
    }
}
