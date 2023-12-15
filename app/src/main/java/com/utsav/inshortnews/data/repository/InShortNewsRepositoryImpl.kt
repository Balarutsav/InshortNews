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
    override suspend fun getHealthNews(): ApiResources<NewsResponse> {
        val response = api.getHealthNews()
        return baseDataSource.getResult { response }
    }


}