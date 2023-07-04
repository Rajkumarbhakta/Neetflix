package com.rkbapps.neetflix.adapter.series

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rkbapps.neetflix.activityes.SeasonsDetailsActivity
import com.rkbapps.neetflix.adapter.series.SeasonAdapter.SeasonViewHolder
import com.rkbapps.neetflix.databinding.SeasonItemBinding
import com.rkbapps.neetflix.models.tvseries.Season

class SeasonAdapter(
    private val context: Context,
    private val seasonList: List<Season>,
    private val tvID: Int
) : RecyclerView.Adapter<SeasonViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        return SeasonViewHolder(
            SeasonItemBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: SeasonViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {

        holder.binding.season = seasonList[position]
        holder.itemView.setOnClickListener {
            val i = Intent(context, SeasonsDetailsActivity::class.java)
            i.putExtra("tvID", tvID)
            i.putExtra("seasonsNumber", seasonList[position].seasonNumber)
            i.putExtra("seasonsName", seasonList[position].name)
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return seasonList.size
    }

    inner class SeasonViewHolder(itemView: SeasonItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val binding = itemView
    }
}