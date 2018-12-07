package com.example.kon3050.lastfm.di.modules

import com.example.kon3050.lastfm.data.domain.Repository
import com.example.kon3050.lastfm.data.repository.RepositoryImpl
import com.example.kon3050.lastfm.di.scopes.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @FragmentScope
    @Provides
    fun providesRepository(repository: RepositoryImpl): Repository = repository
}