package com.utsav.inshortnews.data.remote

import com.utsav.inshortnews.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.GET
import retrofit2.http.Path


interface InShortNewsApiService {

    @GET("NewsAPI/top-headlines/category/{type}/in.json")
    suspend fun getNewsList(
        @Path("type") type : String
    ): Response<NewsResponse>


}

