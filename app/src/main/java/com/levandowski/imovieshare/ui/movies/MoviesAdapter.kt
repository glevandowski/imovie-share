package com.levandowski.imovieshare.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.levandowski.imovieshare.R
import com.levandowski.imovieshare.model.Movie
import kotlinx.android.synthetic.main.movie_card.view.*

class MoviesAdapter(
    private val onClickListener: (movie: Movie) -> Unit
) : PagedListAdapter<Movie, MoviesAdapter.ViewHolder>(Movie.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).run {
            inflate(R.layout.movie_card, parent, false)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.run {
            tv_title_movie_card.text = getItem(position)?.title ?: ""
            setOnClickListener {
               getItem(position)?.let { it1 -> onClickListener.invoke(it1) }
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
