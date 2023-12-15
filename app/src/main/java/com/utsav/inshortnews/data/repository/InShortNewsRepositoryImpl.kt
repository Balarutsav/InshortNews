package com.utsav.inshortnews.data.repository

import com.utsav.inshortnews.data.model.NewsResponse
import com.utsav.inshortnews.data.remote.ApiConstant
import com.utsav.inshortnews.data.remote.ApiResources
import com.utsav.inshortnews.data.remote.BaseDataSource
import com.utsav.inshortnews.data.remote.InShortNewsApiService
import javax.inject.Inject

class InShortNewsRepositoryImpl @Inject constructor(val api: InShortNewsApiService, private val baseDataSource: BaseDataSource,
) :
    InShortNewsRepository {
    override suspend fun getNewsList(type : String): ApiResources<NewsResponse> {
        val response = api.getNewsList(type = type)
        return baseDataSource.getResult { response }
    }


}