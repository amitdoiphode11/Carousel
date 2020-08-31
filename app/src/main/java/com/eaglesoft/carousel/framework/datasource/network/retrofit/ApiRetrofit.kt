package com.eaglesoft.carousel.framework.datasource.network.retrofit

import com.eaglesoft.carousel.framework.datasource.network.model.UserBaseNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRetrofit {

    @GET("api/0.4")
    suspend fun get(@Query("randomapi") randomapi: String? = null): UserBaseNetworkEntity
}