package com.rkbapps.neetflix.db

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rkbapps.neetflix.activityes.MoviePreviewActivity
import com.rkbapps.neetflix.activityes.TvSeriesPreviewActivity
import com.rkbapps.neetflix.databinding.MovieListChildItemsBinding
import com.rkbapps.neetflix.db.BookmarkAdapter.BookMarkViewHolder
import com.rkbapps.neetflix.models.movies.MovieResult
import com.rkbapps.neetflix.utils.ContentType

class BookmarkAdapter(
    private val context: Context,
    private val entityModelList: List<EntityModel>
) : RecyclerView.Adapter<BookMarkViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookMarkViewHolder {
        return BookMarkViewHolder(
            MovieListChildItemsBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: BookMarkViewHolder,
        position: Int
    ) {
        val movieData = MovieResult(
            entityModelList[position].adult,
            entityModelList[position].id,
            entityModelList[position].posterPath,
            entityModelList[position].releaseDate,
            entityModelList[position].title,
            entityModelList[position].voteAverage
        )

        holder.binding.movieData = movieData

        if (entityModelList[position].adult) {
            holder.binding.imgNsfw.visibility = View.VISIBLE
        } else {
            holder.binding.imgNsfw.visibility = View.INVISIBLE
        }
        holder.itemView.setOnClickListener {
            if (entityModelList[position].type == ContentType.MOVIE) {
                val i = Intent(context, MoviePreviewActivity::class.java)
                i.putExtra("id", entityModelList[position].id)
                i.putExtra("tittle", entityModelList[position].title)
                context.startActivity(i)
            }
            if (entityModelList[position].type == ContentType.SERIES) {
                val i = Intent(context, TvSeriesPreviewActivity::class.java)
                i.putExtra("id", entityModelList[position].id)
                i.putExtra("tittle", entityModelList[position].title)
                context.startActivity(i)
            }
        }
    }

    override fun getItemCount(): Int {
        return entityModelList.size
    }

    class BookMarkViewHolder(itemView: MovieListChildItemsBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val binding: MovieListChildItemsBinding = itemView
    }
}