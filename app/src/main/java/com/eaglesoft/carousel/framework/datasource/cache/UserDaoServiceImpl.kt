package com.eaglesoft.carousel.framework.datasource.cache

import com.eaglesoft.carousel.framework.datasource.cache.database.UserDao
import com.eaglesoft.carousel.framework.datasource.cache.model.UserCacheEntity

class UserDaoServiceImpl
constructor(
    private val userDao: UserDao
) : UserDaoService {

    override suspend fun insert(userEntity: UserCacheEntity): Long {
        return userDao.insert(userEntity)
    }

    override suspend fun getList(): List<UserCacheEntity> {
        return userDao.getList()
    }

    override suspend fun get(): UserCacheEntity {
        return userDao.get()
    }
}