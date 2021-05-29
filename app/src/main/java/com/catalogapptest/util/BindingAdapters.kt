package com.catalogapptest.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    Picasso.get().load(url).resize(150, 100).into(view)
}

@BindingAdapter("minAge", "maxAge")
fun concatAge(view: TextView, min: Int, max: Int) {
    val ageRange = "Age: $min-$max"
    view.text = ageRange
}