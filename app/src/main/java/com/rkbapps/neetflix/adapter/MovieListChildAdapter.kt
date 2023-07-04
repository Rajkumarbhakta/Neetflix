package com.rkbapps.neetflix.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rkbapps.neetflix.activityes.MoviePreviewActivity
import com.rkbapps.neetflix.adapter.MovieListChildAdapter.MovieViewHolder
import com.rkbapps.neetflix.databinding.MovieListChildItemsBinding
import com.rkbapps.neetflix.models.movies.MovieResult

class MovieListChildAdapter(
    private val movieList: List<MovieResult>,
    private val context: Context
) :
    RecyclerView.Adapter<MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            MovieListChildItemsBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {
        holder.binding.movieData = movieList[position]

        if (movieList[position].adult) {
            holder.binding.imgNsfw.visibility = View.VISIBLE
        } else {
            holder.binding.imgNsfw.visibility = View.INVISIBLE
        }
        holder.itemView.setOnClickListener {
            val i = Intent(context, MoviePreviewActivity::class.java)
            i.putExtra("id", movieList[position].id)
            i.putExtra("tittle", movieList[position].title)
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class MovieViewHolder(itemView: MovieListChildItemsBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var binding: MovieListChildItemsBinding = itemView
    }
}