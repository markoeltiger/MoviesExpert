package com.mark.moviesexpert.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("load")
fun loadImage(view: ImageView, url: String?) {
    print( "loadImage${Constants.API_IMAGE_PATH}${url}}")
    var murl = "${Constants.API_IMAGE_PATH}${url}"
    Glide.with(view).load(murl).into(view)

}