package com.example.kon3050.lastfm.di

import android.content.Context
import com.example.kon3050.lastfm.LastFmApplication
import com.example.kon3050.lastfm.di.components.ApplicationComponent
import com.example.kon3050.lastfm.di.components.DaggerApplicationComponent
import com.example.kon3050.lastfm.di.components.DaggerFragmentComponent
import com.example.kon3050.lastfm.di.components.FragmentComponent
import com.example.kon3050.lastfm.di.modules.NetworkModule
import com.example.kon3050.lastfm.di.modules.RepositoryModule
import com.example.kon3050.lastfm.di.modules.StorageModule

class ComponentsFactory {

    companion object {

        @JvmStatic
        fun createApplicationComponent(context: Context):ApplicationComponent {
            return DaggerApplicationComponent.builder()
                    .application(context as LastFmApplication)
                    .build()
        }

        @JvmStatic
        fun createUserComponent(applicationComponent: ApplicationComponent): FragmentComponent {
            return DaggerFragmentComponent.builder()
                    .applicationComponent(applicationComponent)
                    .networkModule(NetworkModule())
                    .repositoryModule(RepositoryModule())
                    .storageModule(StorageModule())
                    .build()
        }
    }
}