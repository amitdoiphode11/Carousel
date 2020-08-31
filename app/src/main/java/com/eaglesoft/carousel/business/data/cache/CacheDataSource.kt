package com.eaglesoft.carousel.business.data.cache

import com.eaglesoft.carousel.business.domain.models.User

interface CacheDataSource {

    suspend fun insert(user: User): Long

    suspend fun insertList(users: List<User>)

    suspend fun getList(): List<User>

    suspend fun get(): User

    suspend fun getRandom(): User
}