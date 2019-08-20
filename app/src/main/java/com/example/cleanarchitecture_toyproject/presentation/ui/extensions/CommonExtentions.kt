package com.example.cleanarchitecture_toyproject.presentation.ui.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadUrl(imageUrl : String) {
    Glide.with(context).load(imageUrl).into(this)
}