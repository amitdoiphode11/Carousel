package com.eaglesoft.carousel.business.data.network

import com.eaglesoft.carousel.business.domain.models.User

interface NetworkDataSource {

    suspend fun get(): List<User>?
}