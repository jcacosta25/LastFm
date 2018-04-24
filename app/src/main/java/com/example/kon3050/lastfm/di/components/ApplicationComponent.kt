package com.example.kon3050.lastfm.di.components

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.example.kon3050.lastfm.LastFmApplication
import com.example.kon3050.lastfm.data.api.LastFmApi
import com.example.kon3050.lastfm.data.db.AppDataBase
import com.example.kon3050.lastfm.data.executor.ThreadExecutor
import com.example.kon3050.lastfm.di.modules.ActivityBindingModule
import com.example.kon3050.lastfm.di.modules.ApplicationModule
import com.example.kon3050.lastfm.ui.utils.DeviceUtils
import com.example.kon3050.lastfm.ui.utils.PermissionsManager
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
        ApplicationModule::class,
        AndroidInjectionModule::class,
        ActivityBindingModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun context(): Context

    fun threadExecutor(): ThreadExecutor


    fun deviceUtils(): DeviceUtils

    fun appDataBase(): AppDataBase

    fun permissionsManager(): PermissionsManager

    fun musicMatchApi(): LastFmApi

    fun resources(): Resources

    fun inject(application: LastFmApplication)
}