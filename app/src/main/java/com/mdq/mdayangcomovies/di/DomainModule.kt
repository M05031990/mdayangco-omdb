package com.mdq.mdayangcomovies.di

import com.mdq.mdayangcomovies.data.api.OMDBAPiService
import com.mdq.mdayangcomovies.ui.omdb.OMDBPagingSource
import com.mdq.mdayangcomovies.data.datasource.OMDBDataSource
import com.mdq.mdayangcomovies.data.room.AppDatabase
import com.mdq.mdayangcomovies.domain.GetDetailUseCase
import com.mdq.mdayangcomovies.utils.AppUtility
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [DataSourceModule::class])
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Singleton
    @Provides
    fun providesGetDetailUseCase(omdbDataSource: OMDBDataSource): GetDetailUseCase =
        GetDetailUseCase(omdbDataSource)

}