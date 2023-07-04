package com.rkbapps.neetflix.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rkbapps.neetflix.adapter.GenreAdapter.GenreViewHolder
import com.rkbapps.neetflix.databinding.GenreItemBinding
import com.rkbapps.neetflix.models.Genre

class GenreAdapter(var context: Context, var genres: List<Genre>) :
    RecyclerView.Adapter<GenreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            GenreItemBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.binding.genre = genres[position]
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    class GenreViewHolder(itemView: GenreItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        val binding = itemView
    }
}