package com.catalogapptest.util

import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    url?.let {
        Picasso.get().load(it).resize(150, 100).into(view)
    }
}

@BindingAdapter("imageUrlDetail")
fun loadPoster(view: ImageView, url: String?) {
    url?.let {
        Picasso.get().load(it).into(view)
    }
}

@BindingAdapter("minAge", "maxAge")
fun concatAge(view: TextView, min: Int, max: Int) {
    val ageRange = "Age: $min-$max"
    view.text = ageRange
}

@BindingAdapter("loadHtml")
fun loadHtmlData(view: WebView, data: String?) {
    if (data != null)
        view.loadData(data, "text/html; charset=utf-8", "UTF-8")
}