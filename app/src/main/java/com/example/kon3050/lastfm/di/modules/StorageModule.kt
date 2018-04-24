package com.example.kon3050.lastfm.di.modules

import com.example.kon3050.lastfm.data.db.AppDataBase
import com.example.kon3050.lastfm.data.db.datasource.DiskDataSourceImpl
import com.example.kon3050.lastfm.data.repository.datasource.DiskDataSource
import com.example.kon3050.lastfm.di.scopes.FragmentScope
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class StorageModule @Inject constructor() {

    @FragmentScope
    @Provides
    fun providesMusicDiskDataSource(dataBase: AppDataBase): DiskDataSource {
        return DiskDataSourceImpl(dataBase)
    }
}