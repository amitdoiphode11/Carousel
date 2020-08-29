package com.eaglesoft.carousel.business.interactors

import com.eaglesoft.carousel.business.data.cache.CacheDataSource
import com.eaglesoft.carousel.business.data.network.NetworkDataSource
import com.eaglesoft.carousel.business.domain.models.User
import com.eaglesoft.carousel.business.domain.state.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUsers
constructor(
    private val cacheDataSource: CacheDataSource,
    private val networkDataSource: NetworkDataSource
) {

    /**
     * Show loading
     * Get users from network
     * Insert users into cache
     * Show List<User>
     */
    suspend fun execute(): Flow<DataState<List<User>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        val networkUsers = networkDataSource.get()
        networkUsers?.let { cacheDataSource.insertList(it) }
        val cachedUsers = cacheDataSource.get()
        emit(DataState.Success(cachedUsers))
    }

}
















