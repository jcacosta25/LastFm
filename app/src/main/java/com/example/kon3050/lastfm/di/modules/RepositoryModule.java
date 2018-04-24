package com.example.kon3050.lastfm.di.modules;

import com.example.kon3050.lastfm.data.domain.Repository;
import com.example.kon3050.lastfm.data.repository.RepositoryImpl;
import com.example.kon3050.lastfm.di.scopes.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @FragmentScope
    @Provides
    public Repository providesRepository(RepositoryImpl repository) {
        return repository;
    }
}
