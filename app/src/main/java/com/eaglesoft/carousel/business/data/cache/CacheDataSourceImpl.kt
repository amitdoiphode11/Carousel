package com.eaglesoft.carousel.business.data.cache

import com.eaglesoft.carousel.business.domain.models.User
import com.eaglesoft.carousel.framework.datasource.cache.UserDaoService
import com.eaglesoft.carousel.framework.datasource.cache.mappers.CacheMapper

class CacheDataSourceImpl
constructor(
    private val userDaoService: UserDaoService,
    private val cacheMapper: CacheMapper
) : CacheDataSource {

    override suspend fun insert(user: User): Long {
        return userDaoService.insert(cacheMapper.mapToEntity(user))
    }

    override suspend fun insertList(users: List<User>) {
        for (blog in users) {
            userDaoService.insert(cacheMapper.mapToEntity(blog))
        }
    }

    override suspend fun getList(): List<User> {
        return cacheMapper.mapFromEntityList(userDaoService.getList())
    }

    override suspend fun get(): User {
        return cacheMapper.mapFromEntity(userDaoService.get())
    }
}
