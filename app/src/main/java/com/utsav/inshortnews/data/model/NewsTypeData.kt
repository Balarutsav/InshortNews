package com.utsav.inshortnews.data.model

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

data class NewsTypeData(
    val type: String,
    @DrawableRes val image: Int, var isSelected: Boolean=false
)
