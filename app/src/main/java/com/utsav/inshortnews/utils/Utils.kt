package com.utsav.inshortnews.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class Utils {
}

@BindingAdapter("app:srcCompat")
fun setImageResource(imageView: ImageView, @DrawableRes resourceId: Int) {
    Glide.with(imageView).load(resourceId).into(imageView)
}
@BindingAdapter("imageUrl")
fun loadImageFromUrl(view: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(view.context)
            .load(it)
            .fitCenter()
            .into(view)
    }
}