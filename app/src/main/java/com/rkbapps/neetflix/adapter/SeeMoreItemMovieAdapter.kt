package com.rkbapps.neetflix.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rkbapps.neetflix.activityes.MoviePreviewActivity
import com.rkbapps.neetflix.databinding.MovieListChildItemsBinding
import com.rkbapps.neetflix.models.movies.MovieResult

class SeeMoreItemMovieAdapter :
    ListAdapter<MovieResult, SeeMoreItemMovieAdapter.ViewHolder>(COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MovieListChildItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.movieData = item
        if (item.adult) {
            holder.binding.imgNsfw.visibility = ViewGroup.VISIBLE
        } else {
            holder.binding.imgNsfw.visibility = ViewGroup.INVISIBLE
        }

        holder.itemView.setOnClickListener {
            val i = Intent(holder.itemView.context, MoviePreviewActivity::class.java)
            i.putExtra("id", item.id)
            i.putExtra("tittle", item.title)
            holder.itemView.context.startActivity(i)
        }
    }


    class ViewHolder(itemView: MovieListChildItemsBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val binding = itemView
    }


    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<MovieResult>() {
            override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
                return oldItem == newItem
            }
        }
    }
}