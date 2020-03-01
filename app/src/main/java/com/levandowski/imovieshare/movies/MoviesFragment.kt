package com.levandowski.imovieshare.movies

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.levandowski.imovieshare.model.Movie
import com.levandowski.imovieshare.R
import kotlinx.android.synthetic.main.movies_fragment.*

class MoviesFragment : Fragment() {

    companion object {
       private const val NUMBER_COLUMNS = 2
    }

    private lateinit var viewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        setupToRecyclerView(
            adapter = getAdapter(),
            layoutManager = GridLayoutManager(
                activity?.applicationContext,
                NUMBER_COLUMNS
            )
        )
    }

    private fun getMockMovies() = listOf(
        Movie(
            title = "Tarantino",
            overview = "Is happy",
            voteAverage = 1.0
        ),
        Movie(
            title = "Star wars",
            overview = "Is happy",
            voteAverage = 6.0
        ),
        Movie(
            title = "Tarantino",
            overview = "Is happy",
            voteAverage = 6.0
        ),
        Movie(
            title = "Star wars",
            overview = "Is happy",
            voteAverage = 6.0
        ),
        Movie(
            title = "Tarantino",
            overview = "Is happy",
            voteAverage = 6.0
        ),
        Movie(
            title = "Star wars",
            overview = "Is happy",
            voteAverage = 6.0
        ),
        Movie(
            title = "Tarantino",
            overview = "Is happy",
            voteAverage = 6.0
        ),
        Movie(
            title = "Star wars",
            overview = "Is happy"
        ),
        Movie(
            title = "Tarantino",
            overview = "Is happy",
            voteAverage = 6.0
        ),
        Movie(
            title = "Lord of the rings",
            overview = "Is bad",
            voteAverage = 6.0
        )
    )

    private fun getAdapter() = MovieAdapter(getMockMovies()) { movie ->
        val action = MoviesFragmentDirections.actionMoviesFragmentToAboutMovieFragment()
        action.movie = Gson().toJson(movie).toString()
        findNavController().navigate(action)
    }

    private fun setupToRecyclerView(
        adapter: RecyclerView.Adapter<*>,
        layoutManager: RecyclerView.LayoutManager
    ) {
        recycler_view.run {
            setLayoutManager(layoutManager)
            setAdapter(adapter)
        }
    }
}
