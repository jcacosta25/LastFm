package com.example.kon3050.lastfm.di.modules

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.content.res.Resources
import com.example.kon3050.lastfm.AppExecutors
import com.example.kon3050.lastfm.data.api.LastFmApi
import com.example.kon3050.lastfm.data.api.factory.ServiceFactory
import com.example.kon3050.lastfm.data.api.interceptors.ApiInterceptor
import com.example.kon3050.lastfm.data.db.AppDataBase
import com.example.kon3050.lastfm.data.db.DATABASE_NAME
import com.example.kon3050.lastfm.data.executor.JobExecutor
import com.example.kon3050.lastfm.data.executor.ThreadExecutor
import com.example.kon3050.lastfm.ui.utils.DeviceUtils
import com.example.kon3050.lastfm.ui.utils.PermissionsManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    internal fun providesApplicationContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    internal fun providesResources(application: Application): Resources {
        return application.resources
    }


    @Provides
    @Singleton
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    /*@Provides
    @Singleton
    internal fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }*/

    @Provides
    @Singleton
    internal fun providesDeviceUtils(application: Application): DeviceUtils {
        return DeviceUtils(application)
    }

    @Provides
    @Singleton
    internal fun providesAppDataBase(application: Application): AppDataBase {
        return Room
                .databaseBuilder(application, AppDataBase::class.java, DATABASE_NAME)
                .build()
    }

    @Provides
    @Singleton
    internal fun providesPermissionsManager(application: Application): PermissionsManager {
        return PermissionsManager(application)
    }


    @Singleton
    @Provides
    fun providesMusicMatchApi(apiInterceptor: ApiInterceptor): LastFmApi {
        return ServiceFactory.createRetrofitService(LastFmApi::class.java,
                "http://ws.audioscrobbler.com/2.0/",
                apiInterceptor)
    }

    @Provides
    @Singleton
    internal fun providesAppExecutors(appExecutors: AppExecutors): AppExecutors {
        return appExecutors
    }
}