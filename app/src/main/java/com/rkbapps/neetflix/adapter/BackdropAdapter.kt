package com.rkbapps.neetflix.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rkbapps.neetflix.activityes.ImagePreviewActivity
import com.rkbapps.neetflix.adapter.BackdropAdapter.BackdropViewHolder
import com.rkbapps.neetflix.databinding.BackdropItemsBinding
import com.rkbapps.neetflix.models.images.Backdrop

class BackdropAdapter(private val context: Context, private val backdrops: List<Backdrop>) :
    RecyclerView.Adapter<BackdropViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackdropViewHolder {
        return BackdropViewHolder(
            BackdropItemsBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: BackdropViewHolder,
        position: Int
    ) {
        holder.binding.backdrop = backdrops[position]

        holder.itemView.setOnClickListener {
            val i = Intent(context, ImagePreviewActivity::class.java)
            i.putExtra("imageKey", backdrops[position].filePath)
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return backdrops.size
    }

    inner class BackdropViewHolder(itemView: BackdropItemsBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val binding = itemView
    }
}