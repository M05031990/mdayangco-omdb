package com.mdq.mdayangcomovies.di

import com.mdq.mdayangcomovies.data.api.OMDBAPiService
import com.mdq.mdayangcomovies.ui.omdb.OMDBPagingSource
import com.mdq.mdayangcomovies.data.datasource.OMDBDataSource
import com.mdq.mdayangcomovies.data.room.AppDatabase
import com.mdq.mdayangcomovies.utils.AppUtility
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [APIModule::class, AppModule::class, DBModule::class])
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun providesOMDBDataSource(
        omdbaPiService: OMDBAPiService,
        appDatabase: AppDatabase,
        appUtility: AppUtility
    ): OMDBDataSource = OMDBDataSource(omdbaPiService, appDatabase, appUtility)

    @Singleton
    @Provides
    fun providesMoviePagingSource(omdbDataSource: OMDBDataSource): OMDBPagingSource =
        OMDBPagingSource(omdbDataSource)

}