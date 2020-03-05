package com.levandowski.imovieshare.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.levandowski.imovieshare.R
import com.levandowski.imovieshare.model.Movie
import com.levandowski.imovieshare.util.Urls
import com.squareup.picasso.Picasso
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
            Picasso.get()
                .load(Urls.BASE_URL_IMAGE_SIZE_SMALL + getItem(position)?.posterPath)
                .fit()
                .centerCrop()
                .into(iv_movie_card)

            setOnClickListener {
                getItem(position)?.let { onClickListener.invoke(it) }
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
