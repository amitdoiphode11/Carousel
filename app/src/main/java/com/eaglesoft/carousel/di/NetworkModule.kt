package com.eaglesoft.carousel.di

import com.eaglesoft.carousel.business.data.network.NetworkDataSource
import com.eaglesoft.carousel.business.data.network.NetworkDataSourceImpl
import com.eaglesoft.carousel.business.domain.models.User
import com.eaglesoft.carousel.business.domain.util.EntityMapper
import com.eaglesoft.carousel.framework.datasource.network.ApiRetrofitService
import com.eaglesoft.carousel.framework.datasource.network.ApiRetrofitServiceImpl
import com.eaglesoft.carousel.framework.datasource.network.retrofit.ApiRetrofit
import com.eaglesoft.carousel.framework.datasource.network.mappers.NetworkMapper
import com.eaglesoft.carousel.framework.datasource.network.model.UserNetworkEntity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkMapper(): EntityMapper<UserNetworkEntity, User>{
        return NetworkMapper()
    }

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient() =
        run {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }

    @Singleton
    @Provides
    fun provideRetrofit(gson:  Gson, okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit.Builder): ApiRetrofit {
        return retrofit
            .build()
            .create(ApiRetrofit::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitService(
        apiRetrofit: ApiRetrofit
    ): ApiRetrofitService{
        return ApiRetrofitServiceImpl(apiRetrofit)
    }

    @Singleton
    @Provides
    fun provideNetworkDataSource(
        apiRetrofitService: ApiRetrofitService,
        networkMapper: NetworkMapper
    ): NetworkDataSource{
        return NetworkDataSourceImpl(apiRetrofitService, networkMapper)
    }

}
