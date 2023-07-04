package com.rkbapps.neetflix.adapter.series

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rkbapps.neetflix.R
import com.rkbapps.neetflix.activityes.EpisodeDetailsActivity
import com.rkbapps.neetflix.adapter.series.EpisodeAdapter.SeasonsDetailsViewHolder
import com.rkbapps.neetflix.databinding.EpisodeItemViewBinding
import com.rkbapps.neetflix.models.tvseries.seasons.EpisodeDetails

class EpisodeAdapter(
    private val context: Context,
    private val episodeDetails: List<EpisodeDetails>
) : RecyclerView.Adapter<SeasonsDetailsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonsDetailsViewHolder {
        return SeasonsDetailsViewHolder(
            EpisodeItemViewBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: SeasonsDetailsViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        if (episodeDetails[position].stillPath == null) {
            Glide.with(context).load(R.drawable.general_backdrop).into(holder.binding.imgStillImage)
        } else {
            Glide.with(context)
                .load("https://image.tmdb.org/t/p/original/" + episodeDetails[position].stillPath)
                .into(holder.binding.imgStillImage)
        }
        holder.binding.txtEpisodeNumber.text = "Episode " + episodeDetails[position].episodeNumber
        try {
            if (episodeDetails[position].runtime / 60 == 0) {
                holder.binding.txtEpisodeRuntime.text =
                    episodeDetails[position].runtime.toString() + "M"
            } else {
                holder.binding.txtEpisodeRuntime.text =
                    (episodeDetails[position].runtime / 60).toString() + "H" + episodeDetails[position].runtime % 60 + "M"
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        holder.binding.txtEpisodeRatting.text = "" + episodeDetails[position].voteAverage
        holder.binding.txtEpisodeName.text = episodeDetails[position].name

        val i = Intent(context, EpisodeDetailsActivity::class.java)
        i.putExtra("episodeDetails", episodeDetails[position])
        holder.itemView.setOnClickListener { context.startActivity(i) }
    }

    override fun getItemCount(): Int {
        return episodeDetails.size
    }

    class SeasonsDetailsViewHolder(itemView: EpisodeItemViewBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val binding: EpisodeItemViewBinding = itemView
    }
}