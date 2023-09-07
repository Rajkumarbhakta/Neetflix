package com.rkbapps.neetflix.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rkbapps.neetflix.R
import com.rkbapps.neetflix.activityes.TvSeriesPreviewActivity
import com.rkbapps.neetflix.adapter.TvSeriesListChildAdapter.TvSeriesViewHolder
import com.rkbapps.neetflix.models.tvseries.TvSeriesResult

class TvSeriesListChildAdapter(
    private val seriesList: List<TvSeriesResult>,
    private val context: Context,
) :
    RecyclerView.Adapter<TvSeriesViewHolder?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvSeriesViewHolder {
        return TvSeriesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_list_child_items, parent, false)
        )
    }


    override fun onBindViewHolder(
        holder: TvSeriesViewHolder,
        @SuppressLint("RecyclerView") position: Int,
    ) {
        if (seriesList[position].posterPath != null) {
            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500${seriesList[position].posterPath}")
                .into(holder.poster)
        } else {
            Glide.with(context).load(R.drawable.default_poster).into(holder.poster)
        }
        holder.tittle.text = seriesList[position].name
        holder.releaseYear.text = seriesList[position].firstAirDate
        holder.ratting.text = "${seriesList[position].voteAverage}"
        holder.nsfw.visibility = View.INVISIBLE
        holder.itemView.setOnClickListener {
            val i = Intent(context, TvSeriesPreviewActivity::class.java)
            i.putExtra("id", seriesList[position].id)
            i.putExtra("tittle", seriesList[position].name)
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return seriesList.size
    }

    class TvSeriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.findViewById(R.id.imgMoviePoster)
        val tittle: TextView = itemView.findViewById(R.id.txtTittle)
        val releaseYear: TextView = itemView.findViewById(R.id.txtReleaseYear)
        val ratting: TextView = itemView.findViewById(R.id.txtRatting)
        val nsfw: ImageView = itemView.findViewById(R.id.imgNsfw)
    }
}