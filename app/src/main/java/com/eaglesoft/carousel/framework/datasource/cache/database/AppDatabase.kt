package com.eaglesoft.carousel.framework.datasource.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eaglesoft.carousel.framework.datasource.cache.model.UserCacheEntity

@Database(entities = [UserCacheEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun blogDao(): UserDao

    companion object {
        val DATABASE_NAME: String = "carousel_db"
    }


}