package com.eaglesoft.carousel.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ProductionModule {


    @Singleton
    @Provides
    fun provideString(): String {
        return "PROD String"
    }
}















