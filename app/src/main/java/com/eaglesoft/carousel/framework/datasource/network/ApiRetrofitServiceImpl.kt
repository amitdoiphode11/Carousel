package com.eaglesoft.carousel.framework.datasource.network

import android.util.Log
import com.eaglesoft.carousel.framework.datasource.network.model.UserBaseNetworkEntity
import com.eaglesoft.carousel.framework.datasource.network.retrofit.ApiRetrofit

class ApiRetrofitServiceImpl
constructor(
    private val apiRetrofit: ApiRetrofit
) : ApiRetrofitService {
    private val TAG = "ApiRetrofitServiceImpl"

    override suspend fun get(): UserBaseNetworkEntity? {
        Log.e(TAG, "get: " + apiRetrofit.get()?.results)
        return apiRetrofit.get()
    }
}