package com.eaglesoft.carousel.business.data.network

import android.util.Log
import com.eaglesoft.carousel.business.domain.models.User
import com.eaglesoft.carousel.framework.datasource.network.ApiRetrofitService
import com.eaglesoft.carousel.framework.datasource.network.mappers.NetworkMapper

class NetworkDataSourceImpl
constructor(
    private val apiRetrofitService: ApiRetrofitService,
    private val networkMapper: NetworkMapper
) : NetworkDataSource {
    private val TAG = "NetworkDataSourceImpl"
    override suspend fun get(): List<User>? {
        return networkMapper.mapFromResultEntityList(apiRetrofitService.get())
    }
}