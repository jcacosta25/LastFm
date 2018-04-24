package com.example.kon3050.lastfm.di.components

import com.example.kon3050.lastfm.di.modules.NetworkModule
import com.example.kon3050.lastfm.di.modules.RepositoryModule
import com.example.kon3050.lastfm.di.modules.StorageModule
import com.example.kon3050.lastfm.di.modules.ViewModelModule
import com.example.kon3050.lastfm.di.scopes.FragmentScope
import com.example.kon3050.lastfm.ui.base.BaseFragment
import com.example.kon3050.lastfm.ui.detail.DetailFragment
import com.example.kon3050.lastfm.ui.home.TopArtistFragment
import dagger.Component


@FragmentScope
@Component(dependencies = [ApplicationComponent::class],
        modules = [
            RepositoryModule::class,
            NetworkModule::class,
            StorageModule::class,
            ViewModelModule::class
        ])
interface FragmentComponent {
    fun inject(topArtistFragment: TopArtistFragment)
    fun inject(detailFragment: DetailFragment)
    fun inject(baseFragment: BaseFragment)
}