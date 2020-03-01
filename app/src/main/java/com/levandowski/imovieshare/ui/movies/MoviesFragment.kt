package com.levandowski.imovieshare.ui.movies

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.levandowski.imovieshare.model.Movie
import com.levandowski.imovieshare.R
import kotlinx.android.synthetic.main.movies_fragment.*
import androidx.paging.PagedList
import androidx.lifecycle.Observer
import com.levandowski.imovieshare.data.MovieDataSource
import com.levandowski.imovieshare.data.remote.NetworkState
import com.levandowski.imovieshare.di.DaggerApplicationComponent
import com.levandowski.imovieshare.di.ViewModelFactory
import javax.inject.Inject

class MoviesFragment : Fragment() {

    @Inject
    lateinit var viewModeFactory: ViewModelFactory

    private val viewModel: MoviesViewModel
        get() = ViewModelProviders.of(this, viewModeFactory).get(MoviesViewModel::class.java)

    init {
        DaggerApplicationComponent.builder().build().inject(this)
    }

    private val adapter = MoviesAdapter { item ->
        MoviesFragmentDirections.actionMoviesFragmentToAboutMovieFragment().apply {
            movie = Gson().toJson(item).toString()
        }.also { action ->
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToRecyclerView(
            adapter = adapter,
            layoutManager = getLayoutManager()
        )
    }

    override fun onResume() {
        super.onResume()
        viewModel.moviesPagedList.observe(this, pagedListObserver)
        viewModel.sourceLiveData.observe(this, dataSourceObserver)
    }

    private val pagedListObserver = Observer<PagedList<Movie>> { moviesFromLiveData ->
        adapter.run {
            submitList(moviesFromLiveData)
            notifyDataSetChanged()
        }
    }

    private val dataSourceObserver = Observer<MovieDataSource> {
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
            NetworkState.LOADING.status -> {

            }
            NetworkState.Status.FAILED -> {
                sr_movies.isRefreshing = false
                Toast.makeText(
                    activity?.applicationContext,
                    networkState.msg,
                    Toast.LENGTH_LONG
                ).show()
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

    companion object {
        private const val NUMBER_COLUMNS = 2
    }
}
