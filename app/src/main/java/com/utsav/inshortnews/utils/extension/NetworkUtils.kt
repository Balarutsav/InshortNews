package com.utsav.inshortnews.utils.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


fun Context.isNetworkConnected(): Boolean {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
    return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
}

fun String.convertToRequestBody(): RequestBody {
    return this.trim().toRequestBody(contentType = "multipart/form-data".toMediaTypeOrNull())
}

fun HashMap<String, String>.convertValuesToRequestBody(): HashMap<String, RequestBody> {
    val requestBodyMap = HashMap<String, RequestBody>()

    for ((key, value) in this) {
        // Convert the value to a RequestBody
        val requestBody = value.convertToRequestBody()
        requestBodyMap[key] = requestBody
    }

    return requestBodyMap
}

fun  HashMap<String, String>.mapToJson(): String {
    val gson = Gson()
    return gson.toJson(this)
}