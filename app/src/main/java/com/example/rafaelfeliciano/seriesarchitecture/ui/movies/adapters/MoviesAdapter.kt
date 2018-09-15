package com.example.rafaelfeliciano.seriesarchitecture.ui.movies.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.rafaelfeliciano.seriesarchitecture.R
import com.example.rafaelfeliciano.seriesarchitecture.model.Movie
import com.example.rafaelfeliciano.seriesarchitecture.util.extensions.inflate
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<Movie>? = mutableListOf()

    @Synchronized
    fun append(items: List<Movie>) {
        val count = itemCount
        this.items!!.addAll(items)
        notifyItemRangeInserted(count, items.size)
    }

    override fun getItemCount(): Int = items?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder(parent.inflate(R.layout.item_movie))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as MovieViewHolder
        holder.bind(items?.get(position)!!)
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Movie) = with(itemView) {
            movie_title.text = item.title
            movie_year.text = itemView.context.resources.getString(R.string.movie_year_format, item.year)
            movie_overview.text = item.overview
        }
    }
}