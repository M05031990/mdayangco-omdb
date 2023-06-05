package com.mdq.mdayangcomovies.di

import android.content.Context
import com.mdq.mdayangcomovies.BuildConfig
import com.mdq.mdayangcomovies.data.api.OMDBAPiService
import com.mdq.mdayangcomovies.utils.AppUtility
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesAppUtility(@ApplicationContext context: Context): AppUtility =
       AppUtility(context)

}