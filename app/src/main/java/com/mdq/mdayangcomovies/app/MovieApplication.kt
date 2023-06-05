package com.mdq.mdayangcomovies.app

import android.app.Application
import dagger.Provides
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@HiltAndroidApp
class MovieApplication : Application() {
}