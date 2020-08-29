package com.eaglesoft.carousel.di

import android.content.Context
import androidx.room.Room
import com.eaglesoft.carousel.business.data.cache.CacheDataSource
import com.eaglesoft.carousel.business.data.cache.CacheDataSourceImpl
import com.eaglesoft.carousel.business.domain.models.User
import com.eaglesoft.carousel.business.domain.util.EntityMapper
import com.eaglesoft.carousel.framework.datasource.cache.UserDaoService
import com.eaglesoft.carousel.framework.datasource.cache.UserDaoServiceImpl
import com.eaglesoft.carousel.framework.datasource.cache.database.UserDao
import com.eaglesoft.carousel.framework.datasource.cache.database.AppDatabase
import com.eaglesoft.carousel.framework.datasource.cache.mappers.CacheMapper
import com.eaglesoft.carousel.framework.datasource.cache.model.UserCacheEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideCacheMapper(): EntityMapper<UserCacheEntity, User>{
        return CacheMapper()
    }

    @Singleton
    @Provides
    fun provideAppDb(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(
                context,
                AppDatabase::class.java,
                AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDAO(appDatabase: AppDatabase): UserDao {
        return appDatabase.blogDao()
    }

    @Singleton
    @Provides
    fun provideUserDaoService(
        userDao: UserDao
    ):UserDaoService{
        return UserDaoServiceImpl(userDao)
    }

    @Singleton
    @Provides
    fun provideCacheDataSource(
        userDaoService: UserDaoService,
        cacheMapper: CacheMapper
    ): CacheDataSource{
        return CacheDataSourceImpl(userDaoService, cacheMapper)
    }


}

























