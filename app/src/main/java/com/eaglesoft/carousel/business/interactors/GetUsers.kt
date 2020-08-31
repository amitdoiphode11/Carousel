package com.eaglesoft.carousel.business.interactors

import android.util.Log
import com.eaglesoft.carousel.business.data.cache.CacheDataSource
import com.eaglesoft.carousel.business.data.network.NetworkDataSource
import com.eaglesoft.carousel.business.domain.models.User
import com.eaglesoft.carousel.business.domain.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUsers
constructor(
    private val cacheDataSource: CacheDataSource,
    private val networkDataSource: NetworkDataSource
) {
    private val TAG = "GetUsers"

    /**
     * Show loading
     * Get users from network
     * Show List<User>
     */
    suspend fun getOnlineUserList(): Flow<DataState<List<User>>> = flow {
        try {
            emit(DataState.Loading)
            val networkUsers = networkDataSource.get()
            emit(DataState.Success(networkUsers))
        } catch (e: Exception) {
            Log.e(TAG, "execute: ", e)
            emit(DataState.Error(e))
        }
    }

    suspend fun getOfflineUser(): Flow<DataState<List<User>>> = flow {
        try {
            emit(DataState.Loading)
            val user = cacheDataSource.getRandom()
            val userList = arrayListOf<User>()
            userList.add(user)
            emit(DataState.Success(userList))
        } catch (e: Exception) {
            Log.e(TAG, "execute: ", e)
            emit(DataState.Error(e))
        }
    }

    /**
     * Show loading
     * insert into cache
     * show user inserted
     */
    suspend fun addFavorite(user: User?): Flow<DataState<User>> = flow {
        try {
            emit(DataState.Loading)
            user?.let { cacheDataSource.insert(it) }
            emit(DataState.Success(cacheDataSource.get()))
        } catch (e: Exception) {
            Log.e(TAG, "addFavorite: ", e)
            emit(DataState.Error(e))
        }
    }
}
















