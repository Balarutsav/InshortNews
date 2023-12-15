package com.utsav.inshortnews.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class Utils {
}

@BindingAdapter("app:srcCompat")
fun setImageResource(imageView: ImageView, @DrawableRes resourceId: Int) {
    Glide.with(imageView).load(resourceId).into(imageView)
}
@BindingAdapter("formattedDate")
fun setFormattedDate(view: TextView, originalDate: String?) {
    if (originalDate != null) {
        try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
            inputFormat.timeZone = TimeZone.getTimeZone("UTC")
            val date = inputFormat.parse(originalDate)

            val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.US)
            val formattedDate = outputFormat.format(date)

            view.text = formattedDate
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }
}
@BindingAdapter("imageUrl")
fun loadImageFromUrl(view: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(view.context)
            .load(it)
            .centerCrop( )
            .into(view)
    }
}
enum class NewsType(val fullName: String) {
    Sports("sports"),
    Business("business"),
    Tech("technology");

    companion object {
        // Function to get a NewsType enum value by full name
        fun getByFullName(fullName: String): NewsType? {
            return values().find { it.fullName == fullName }
        }

        // Function to get the full name by abbreviation (name)
        fun getFullNameState(abbreviation: String): String? {
            val type = values().find { it.name == abbreviation }
            return type?.fullName
        }
    }
}