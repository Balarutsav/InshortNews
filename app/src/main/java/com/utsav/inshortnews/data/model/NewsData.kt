package com.utsav.inshortnews.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsData(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String?,
    val url: String,
    val urlToImage: String
) : Parcelable {
    @Parcelize
    data class Source(
        var id: String?="", val name: String
    ) : Parcelable
}