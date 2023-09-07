package com.rkbapps.neetflix.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rkbapps.neetflix.R
import com.rkbapps.neetflix.activityes.TvSeriesPreviewActivity
import com.rkbapps.neetflix.databinding.MovieListChildItemsBinding
import com.rkbapps.neetflix.models.tvseries.TvSeriesResult

class SeeMoreItemTvSeriesAdapter :
    ListAdapter<TvSeriesResult, SeeMoreItemTvSeriesAdapter.ViewHolder>(diffUtil) {


    class ViewHolder(itemView: MovieListChildItemsBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val binding = itemView
    }

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

        if (item.posterPath != null) {
            Glide.with(holder.itemView.context)
                .load("https://image.tmdb.org/t/p/w500" + item.posterPath)
                .into(holder.binding.imgMoviePoster)
        } else {
            Glide.with(holder.itemView.context).load(R.drawable.default_poster)
                .into(holder.binding.imgMoviePoster)
        }

        holder.binding.txtTittle.text = item.name

        holder.binding.txtReleaseYear.text = item.firstAirDate

        holder.binding.txtRatting.text = "${item.voteAverage}"


        holder.binding.imgNsfw.visibility = View.INVISIBLE

        holder.itemView.setOnClickListener {
            val i = Intent(holder.itemView.context, TvSeriesPreviewActivity::class.java)
            i.putExtra("id", item.id)
            i.putExtra("tittle", item.name)
            holder.itemView.context.startActivity(i)
        }

    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<TvSeriesResult>() {
            override fun areItemsTheSame(
                oldItem: TvSeriesResult,
                newItem: TvSeriesResult,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TvSeriesResult,
                newItem: TvSeriesResult,
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

}