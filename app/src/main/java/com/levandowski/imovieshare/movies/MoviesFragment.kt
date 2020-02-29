package com.levandowski.imovieshare.movies

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.levandowski.imovieshare.model.Movie
import com.levandowski.imovieshare.R

class MoviesFragment : Fragment() {

    companion object {
        const val NUMBER_COLUMNS = 2
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
        Movie(title = "tarantino"),
        Movie(title = "Star wars"),
        Movie(title = "tarantino"),
        Movie(title = "Star wars"),
        Movie(title = "tarantino"),
        Movie(title = "Star wars"),
        Movie(title = "tarantino"),
        Movie(title = "Star wars"),
        Movie(title = "tarantino"),
        Movie(title = "Star wars")
    )

    private fun getAdapter() = MovieAdapter(getMockMovies()) { movie ->
        val action = MoviesFragmentDirections.actionMoviesFragmentToAboutMovieFragment()
        movie.title?.let { action.setTitleMovie(it) }
        view?.findNavController()?.navigate(action)
    }

    private fun setupToRecyclerView(
        adapter: RecyclerView.Adapter<*>,
        layoutManager: RecyclerView.LayoutManager
    ) {
        view?.findViewById<RecyclerView>(R.id.recycler_view)?.run {
            setLayoutManager(layoutManager)
            setAdapter(adapter)
        }
    }
}
