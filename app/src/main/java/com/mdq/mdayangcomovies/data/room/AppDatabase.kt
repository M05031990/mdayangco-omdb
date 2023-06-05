package com.mdq.mdayangcomovies.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mdq.mdayangcomovies.data.model.OMDBModel
import com.mdq.mdayangcomovies.data.room.converters.RatingConverter
import com.mdq.mdayangcomovies.data.room.dao.OMDBDao

@Database(entities = [OMDBModel::class], version = AppDatabase.DB_VERSION, exportSchema = false)
@TypeConverters(RatingConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun omdbDao():OMDBDao
    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "db-room-movies"
    }
}