package com.utsav.inshortnews.ui.newsList

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utsav.inshortnews.data.model.NewsResponse
import com.utsav.inshortnews.data.remote.ApiResources
import com.utsav.inshortnews.data.repository.InShortNewsRepository
import com.utsav.inshortnews.utils.extension.isNetworkConnected
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewListViewModel @Inject constructor(
 val application: Application,
    val repository: InShortNewsRepository
) :
    ViewModel() {

    val TAG = "NewList ViewModel"
    lateinit var context: Context

    private val mStateNewsResponse =
        MutableStateFlow<ApiResources<NewsResponse>>(ApiResources.unknown())
    val stateNewsResponse: StateFlow<ApiResources<NewsResponse>> get() = mStateNewsResponse

    init {

        context = application.applicationContext

    }


    fun getNewsList(type : String) {


        viewModelScope.launch {

            if (context.isNetworkConnected()) {

                try {
                    mStateNewsResponse.value = ApiResources.loading()

                    val response = repository.getNewsList(type = type)
                    launch(Dispatchers.Main) {

                        mStateNewsResponse.value = response
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    mStateNewsResponse.value = ApiResources.error("Something get wrong")

                    Log.e(TAG, "called ${e.message}")
                }
            } else {
                launch(Dispatchers.Main) {
                    mStateNewsResponse.value = ApiResources.noInternetConnection()

                }

            }


        }

    }

}