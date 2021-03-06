package com.eaglesoft.carousel.framework.datasource.network.model

data class UserNetworkEntity(
    var gender: String,
    val name: NameNetworkEntity,
    val location: LocationNetworkEntity,
    var email: String,
    var picture: String,
    var username: String,
    var registered: Long,
    var dob: Long,
    var phone: String,
    var cell: String,
    var SSN: String
)



