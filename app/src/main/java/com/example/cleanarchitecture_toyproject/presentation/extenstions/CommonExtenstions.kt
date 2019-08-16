package com.example.cleanarchitecture_toyproject.presentation.extenstions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun <T> List<T>.getItem(index: Int): T?{
    if(index in 0 until size){
        return get(index)
    }
    return null
}


fun ImageView.loadUrl(imageUrl: String) {
    Glide.with(context).load(imageUrl).into(this)
}