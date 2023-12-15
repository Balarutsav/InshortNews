package com.utsav.inshortnews.data.remote

import com.utsav.inshortnews.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.GET


interface InShortNewsApiService {

    @GET("NewsAPI/top-headlines/category/health/in.json")
    suspend fun getHealthNews(): Response<NewsResponse>


}

