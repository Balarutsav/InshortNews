package com.utsav.inshortnews.data.repository

import com.utsav.inshortnews.data.model.NewsResponse
import com.utsav.inshortnews.data.remote.ApiResources

interface InShortNewsRepository {

    suspend fun getNewsList(type : String): ApiResources<NewsResponse>

}