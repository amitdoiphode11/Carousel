package com.eaglesoft.carousel.framework.datasource.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eaglesoft.carousel.framework.datasource.cache.model.UserCacheEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userEntity: UserCacheEntity): Long

    @Query("SELECT * FROM user")
    suspend fun get(): List<UserCacheEntity>
}