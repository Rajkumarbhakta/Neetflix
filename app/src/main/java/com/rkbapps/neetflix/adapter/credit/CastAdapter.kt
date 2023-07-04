package com.rkbapps.neetflix.adapter.credit

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rkbapps.neetflix.activityes.PersonActivity
import com.rkbapps.neetflix.adapter.credit.CastAdapter.CastViewHolder
import com.rkbapps.neetflix.databinding.CastItemViewBinding
import com.rkbapps.neetflix.models.castandcrew.Cast

class CastAdapter(private val context: Context, private val casts: List<Cast>) :
    RecyclerView.Adapter<CastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(
            CastItemViewBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: CastViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        holder.binding.cast = casts[position]

        holder.itemView.setOnClickListener(View.OnClickListener {
            val i = Intent(context, PersonActivity::class.java)
            i.putExtra("id", casts[position].id)
            i.putExtra("name", casts[position].name)
            i.putExtra("image", casts[position].profilePath)
            i.putExtra("gender", casts[position].gender)
            i.putExtra("popularity", casts[position].popularity)
            i.putExtra("department", casts[position].knownForDepartment)
            context.startActivity(i)
        })
    }

    override fun getItemCount(): Int {
        return casts.size
    }

    inner class CastViewHolder(itemView: CastItemViewBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val binding: CastItemViewBinding = itemView
    }
}