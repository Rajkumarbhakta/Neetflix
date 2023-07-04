package com.rkbapps.neetflix.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.rkbapps.neetflix.R

@BindingAdapter("imageUrl")
fun ImageView.loadFromUrl(url: String?) {
    if (url != null)
        Glide.with(this.context).load("https://image.tmdb.org/t/p/w500$url").into(this)
    else
        Glide.with(this.context).load(R.drawable.default_poster).into(this)
}

@BindingAdapter("imageBackdropUrl")
fun ImageView.loadBackdropFromUrl(url: String?) {
    if (url != null)
        Glide.with(this.context).load("https://image.tmdb.org/t/p/w500$url").into(this)
    else
        Glide.with(this.context).load(R.drawable.general_backdrop).into(this)
}