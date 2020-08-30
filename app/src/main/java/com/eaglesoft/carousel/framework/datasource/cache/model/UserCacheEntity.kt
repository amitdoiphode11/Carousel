package com.eaglesoft.carousel.framework.datasource.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class UserCacheEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var gender: String,
    val name: String,
    val location: String,
    var email: String,
    var picture: String,
    var username: String,
    var registered: Long,
    var dob: Long,
    var phone: String,
    var cell: String,
    var SSN: String
)



