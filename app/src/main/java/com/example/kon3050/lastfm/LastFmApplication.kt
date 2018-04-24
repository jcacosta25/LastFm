package com.example.kon3050.lastfm

import android.app.Activity
import android.content.Context
import android.support.multidex.MultiDexApplication
import com.example.kon3050.lastfm.di.ComponentsFactory
import com.example.kon3050.lastfm.di.components.ApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class LastFmApplication : MultiDexApplication(), HasActivityInjector {

    private lateinit var appComponent: ApplicationComponent

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
//        AppInjector.init(this)
        appComponent = ComponentsFactory.createApplicationComponent(this)
        appComponent.inject(this)
    }

    companion object {

        @JvmStatic
        fun getAppComponent(context: Context): ApplicationComponent {
            return (context.applicationContext as LastFmApplication).appComponent
        }
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
}