package com.levandowski.imovieshare.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.levandowski.imovieshare.R
import com.levandowski.imovieshare.model.Movie
import kotlinx.android.synthetic.main.movie_card.view.*

class MovieAdapter(
    private val movies: List<Movie>,
    private val onClickListener: (movie: Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).run {
            inflate(R.layout.movie_card, parent, false)
        }
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.run {
            tv_title_movie_card.text = movies[position].title
            setOnClickListener {
                onClickListener.invoke(movies[position])
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
