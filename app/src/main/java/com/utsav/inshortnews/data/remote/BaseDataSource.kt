package com.utsav.inshortnews.data.remote

import android.content.Context
import com.utsav.inshortnews.R
import retrofit2.Response
import javax.inject.Inject

open class BaseDataSource @Inject constructor(val context: Context) {

    open suspend fun <T> getResult(call: suspend () -> Response<T>): ApiResources<T> {
        try {
            val response = call()

            when {
                response.code() in 400..500 -> {
                    return ApiResources.error(context.getString(R.string.msg_something_went_wrong))
                }

                response.code() == 302 -> {
                    val errorMessage = response.errorBody()?.string()
                    return ApiResources.error(message = errorMessage)
                }

                response.code() == 500 -> {
                    return ApiResources.error(context.getString(R.string.msg_something_went_wrong))
                }

                response.isSuccessful -> {
                    val body = response.body()
                    return if (body != null) {
                        ApiResources.success(body, "")
                    } else {
                        ApiResources.error("")
                    }
                }

                else -> {
                    return error("Error => ${response.code()} ${response.message()}")
                }
            }
        } catch (e: Exception) {
            return error("Error => ${e.message} ?: $e")
        }
    }

    private fun <T> error(message: String): ApiResources<T> {
        return ApiResources.error(message)
    }
}