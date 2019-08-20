package com.example.cleanarchitecture_toyproject.presentation.ui.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun <T> List<T>.getItem(index : Int) : T? {
    //index = 0; index < size
    if(index in 0 until size){
        return get(index)
    }
    return null
}

fun ImageView.loadUrl(imageUrl : String) {
    Glide.with(context).load(imageUrl).into(this)
}