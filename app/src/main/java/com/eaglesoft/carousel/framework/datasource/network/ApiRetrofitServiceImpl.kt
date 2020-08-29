package com.eaglesoft.carousel.framework.datasource.network

import com.eaglesoft.carousel.framework.datasource.network.model.UserBaseNetworkEntity
import com.eaglesoft.carousel.framework.datasource.network.retrofit.ApiRetrofit

class ApiRetrofitServiceImpl
constructor(
    private val apiRetrofit: ApiRetrofit
) : ApiRetrofitService {

    override suspend fun get(): UserBaseNetworkEntity? {
        return apiRetrofit.get()
    }
}