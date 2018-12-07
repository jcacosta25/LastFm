package com.example.kon3050.lastfm.di.modules

import com.example.kon3050.lastfm.data.api.LastFmApi
import com.example.kon3050.lastfm.data.api.datasource.CloudDataSourceImpl
import com.example.kon3050.lastfm.data.repository.datasource.CloudDataSource
import com.example.kon3050.lastfm.di.scopes.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @FragmentScope
    @Provides
    fun provideCloudDataSource(api: LastFmApi): CloudDataSource = CloudDataSourceImpl(api)
}