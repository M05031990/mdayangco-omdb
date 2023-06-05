package com.mdq.mdayangcomovies.di

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.mdq.mdayangcomovies.data.room.AppDatabase
import com.mdq.mdayangcomovies.data.room.dao.OMDBDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {
    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return databaseBuilder(
            context, AppDatabase::class.java, AppDatabase.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}