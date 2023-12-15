package com.utsav.inshortnews.di

import android.app.Application
import android.content.Context
import com.utsav.inshortnews.data.remote.BaseDataSource
import com.utsav.inshortnews.data.remote.InShortNewsApiService
import com.utsav.inshortnews.data.repository.InShortNewsRepository
import com.utsav.inshortnews.data.repository.InShortNewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideDriverRepository(
        api: InShortNewsApiService,
        baseDataSource: BaseDataSource,

        ): InShortNewsRepository {
        return InShortNewsRepositoryImpl(api, baseDataSource)
    }


}