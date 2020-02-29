package com.levandowski.imovieshare.about

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.levandowski.imovieshare.R
import kotlinx.android.synthetic.main.about_movie_fragment.*

class AboutMovieFragment : Fragment() {

    private lateinit var viewModel: AboutMovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.about_movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AboutMovieViewModel::class.java)
        val name = arguments?.let { AboutMovieFragmentArgs.fromBundle(it).titleMovie }
        about_movie_title_tv.text = name.toString()
    }
}
