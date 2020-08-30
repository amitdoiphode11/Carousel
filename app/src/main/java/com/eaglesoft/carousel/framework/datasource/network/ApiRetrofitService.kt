package com.eaglesoft.carousel.framework.datasource.network

import com.eaglesoft.carousel.framework.datasource.network.model.UserBaseNetworkEntity

interface ApiRetrofitService {

    suspend fun get(): UserBaseNetworkEntity
}