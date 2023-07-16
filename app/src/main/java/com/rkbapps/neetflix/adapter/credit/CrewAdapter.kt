package com.rkbapps.neetflix.adapter.credit

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rkbapps.neetflix.activityes.PersonActivity
import com.rkbapps.neetflix.adapter.credit.CrewAdapter.CrewViewHolder
import com.rkbapps.neetflix.databinding.CrewItemViewBinding
import com.rkbapps.neetflix.models.castandcrew.Crew

class CrewAdapter(private val context: Context, private val crews: List<Crew>) :
    RecyclerView.Adapter<CrewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewViewHolder {
        return CrewViewHolder(
            CrewItemViewBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: CrewViewHolder,
        position: Int
    ) {

        holder.binding.crew = crews[position]

        holder.itemView.setOnClickListener {
            val i = Intent(context, PersonActivity::class.java)
            i.putExtra("id", crews[position].id)
            i.putExtra("name", crews[position].name)
            i.putExtra("gender", crews[position].gender)
            i.putExtra("image", crews[position].profilePath?:"")
            i.putExtra("popularity", crews[position].popularity)
            i.putExtra("department", crews[position].knownForDepartment)
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return crews.size
    }

    class CrewViewHolder(itemView: CrewItemViewBinding) : RecyclerView.ViewHolder(itemView.root) {
        val binding: CrewItemViewBinding = itemView
    }
}