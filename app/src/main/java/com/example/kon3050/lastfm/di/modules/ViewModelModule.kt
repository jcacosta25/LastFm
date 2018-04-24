package com.example.kon3050.lastfm.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.kon3050.lastfm.di.scopes.FragmentScope
import com.example.kon3050.lastfm.di.scopes.ViewModelKey
import com.example.kon3050.lastfm.ui.base.ViewModelFactoryEx
import com.example.kon3050.lastfm.ui.detail.DetailArtistViewModel
import com.example.kon3050.lastfm.ui.home.TopArtistViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(TopArtistViewModel::class)
    abstract fun bindTopArtistViewModel(viewModel: TopArtistViewModel):ViewModel


    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(DetailArtistViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailArtistViewModel):ViewModel

    @FragmentScope
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactoryEx): ViewModelProvider.Factory

}