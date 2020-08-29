package com.eaglesoft.carousel.business.domain.models

data class User(
    var id: Int? = null,
    var gender: String,
    var email: String,
    var picture: String,
    var username: String,
    var registered: Long,
    var dob: Long,
    var phone: String,
    var cell: String,
    var SSN: String
)




