package com.rkbapps.neetflix.adapter.person

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rkbapps.neetflix.activityes.ImagePreviewActivity
import com.rkbapps.neetflix.adapter.person.PersonImageAdapter.PersonImageViewHolder
import com.rkbapps.neetflix.databinding.PersonImageItemBinding
import com.rkbapps.neetflix.models.person.images.Profile

class PersonImageAdapter(private val context: Context, private val profileList: List<Profile>) :
    RecyclerView.Adapter<PersonImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonImageViewHolder {
        return PersonImageViewHolder(
            PersonImageItemBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: PersonImageViewHolder,
        position: Int
    ) {

        holder.binding.person = profileList[position]

        holder.itemView.setOnClickListener {
            val i = Intent(context, ImagePreviewActivity::class.java)
            i.putExtra("imageKey", profileList[position].filePath)
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return profileList.size
    }

    inner class PersonImageViewHolder(itemView: PersonImageItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val binding: PersonImageItemBinding = itemView
    }
}