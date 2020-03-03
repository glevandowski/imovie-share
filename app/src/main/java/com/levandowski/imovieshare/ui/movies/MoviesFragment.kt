package com.levandowski.imovieshare.ui.movies

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.levandowski.imovieshare.model.Movie
import com.levandowski.imovieshare.R
import kotlinx.android.synthetic.main.movies_fragment.*
import androidx.paging.PagedList
import androidx.lifecycle.Observer
import com.levandowski.imovieshare.data.remote.movie.MovieDataSource
import com.levandowski.imovieshare.data.remote.NetworkState
import com.levandowski.imovieshare.di.DaggerApplicationComponent
import com.levandowski.imovieshare.di.ViewModelFactory
import com.levandowski.imovieshare.ui.MainActivity
import javax.inject.Inject

class MoviesFragment : Fragment() {

    @Inject
    lateinit var viewModeFactory: ViewModelFactory

    private val viewModel: MoviesViewModel
        get() = ViewModelProviders.of(this, viewModeFactory).get(MoviesViewModel::class.java)

    init {
        DaggerApplicationComponent.builder().build().inject(this)
    }

    private val adapter = MoviesAdapter { item -> navigateToMovieInformation(item) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        when (activity) {
            is MainActivity -> {
                (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
                (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(false)
            }
        }
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()
        setupToRecyclerView(
            adapter = adapter,
            layoutManager = getLayoutManager()
        )
        viewModel.moviesPagedList.observe(this, pagedListObserver)
        viewModel.sourceLiveData.observe(this, movieDataSourceObserver)
    }

    private fun navigateToMovieInformation(itemMovie: Movie) {
        MoviesFragmentDirections.actionMoviesFragmentToAboutMovieFragment().apply {
            movie = itemMovie.wrap()
        }.also { action ->
            findNavController().navigate(action)
        }
    }

    private val pagedListObserver = Observer<PagedList<Movie>> { moviesFromLiveData ->
        adapter.run {
            submitList(moviesFromLiveData)
            notifyDataSetChanged()
        }
    }

    private val movieDataSourceObserver = Observer<MovieDataSource> {
        sr_movies.setOnRefreshListener {
            it.invalidate()
        }

        it.getPaginatedNetworkStateLiveData().observe(this, networkStateObserver)
    }

    private val networkStateObserver = Observer<NetworkState> { networkState ->
        when (networkState.status) {
            NetworkState.LOADED.status -> {
                sr_movies.isRefreshing = false
            }
            NetworkState.Status.FAILED -> {
                sr_movies.isRefreshing = false
                networkState.msg?.showAlert()
            }
            else -> {
            }
        }
    }

    private fun getLayoutManager() = GridLayoutManager(
        requireContext(),
        NUMBER_COLUMNS
    )

    private fun setupToRecyclerView(
        adapter: RecyclerView.Adapter<*>,
        layoutManager: RecyclerView.LayoutManager
    ) {
        rv_movies.run {
            setLayoutManager(layoutManager)
            setAdapter(adapter)
        }
    }

    private fun String.showAlert() =
        Toast.makeText(requireContext(), this, Toast.LENGTH_LONG).show()

    companion object {
        private const val NUMBER_COLUMNS = 2
    }
}
