package com.eaglesoft.carousel.framework.datasource.network.model

data class UserResultsNetworkEntity(
    val user: UserNetworkEntity,
    val seed: String,
    val version: Double
)