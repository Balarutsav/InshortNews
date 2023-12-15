package com.utsav.inshortnews.di


import android.content.Context
import com.google.gson.Gson
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.utsav.inshortnews.data.remote.ApiConstant
import com.utsav.inshortnews.data.remote.BaseDataSource
import com.utsav.inshortnews.data.remote.InShortNewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Singleton
    @Provides
    fun provideGSON(): Gson = Gson()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit =
        Retrofit.Builder().baseUrl(ApiConstant.BASE_URL).client(okHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson)).build()


    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): InShortNewsApiService =
        retrofit.create(InShortNewsApiService::class.java)

    @Singleton
    @Provides
    fun provideBaseOutsources(context: Context) = BaseDataSource(context)

    @Singleton
    @Provides
    fun okHttpClient(): OkHttpClient {
        val levelType: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY

        val logging = HttpLoggingInterceptor()
        logging.setLevel(levelType)

        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()


        val builder = OkHttpClient.Builder()
        builder.addInterceptor(OkHttpProfilerInterceptor())

        httpClient.connectTimeout(5, TimeUnit.MINUTES)
        httpClient.readTimeout(5, TimeUnit.MINUTES)
        httpClient.writeTimeout(5, TimeUnit.MINUTES);

        httpClient.addNetworkInterceptor(logging)
        val client = httpClient.build()
        return client
    }


}