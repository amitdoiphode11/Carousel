package com.eaglesoft.carousel.di

import com.eaglesoft.carousel.business.data.cache.CacheDataSource
import com.eaglesoft.carousel.business.data.network.NetworkDataSource
import com.eaglesoft.carousel.business.interactors.GetUsers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object IntegratorsModule {

    @Singleton
    @Provides
    fun provideGetUsers(
        cacheDataSource: CacheDataSource,
        networkDataSource: NetworkDataSource
    ): GetUsers{
        return GetUsers(cacheDataSource, networkDataSource)
    }
}














