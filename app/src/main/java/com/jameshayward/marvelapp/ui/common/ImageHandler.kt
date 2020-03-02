package com.jameshayward.marvelapp.ui.common

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String) {
    Picasso.with(this.context).load(url).into(this)
}
