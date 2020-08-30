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
     * Insert users into cache
     * Show List<User>
     */
    suspend fun execute(): Flow<DataState<List<User>>> = flow {
        try {
            emit(DataState.Loading)
            val networkUsers = networkDataSource.get()
            emit(DataState.Success(networkUsers))
        } catch (e: Exception) {
            Log.e(TAG, "execute: ", e)
            emit(DataState.Error(e))
        }
    }

    suspend fun addFavorite(user: User?): Flow<DataState<User>> = flow {
        try {
            emit(DataState.Loading)
            user?.let { cacheDataSource.insert(it) }
            emit(DataState.Success(cacheDataSource.get()))
        }catch (e:Exception){
            Log.e(TAG, "addFavorite: ", e)
            emit(DataState.Error(e))
        }
    }
}
















