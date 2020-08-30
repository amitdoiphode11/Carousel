package com.eaglesoft.carousel.framework.datasource.cache

import com.eaglesoft.carousel.framework.datasource.cache.model.UserCacheEntity

interface UserDaoService {

    suspend fun insert(userEntity: UserCacheEntity): Long

    suspend fun getList(): List<UserCacheEntity>

    suspend fun get(): UserCacheEntity

}