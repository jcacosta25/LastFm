package com.example.kon3050.lastfm.di.modules

import com.example.kon3050.lastfm.di.scopes.ActivityScope
import com.example.kon3050.lastfm.ui.base.BaseActivity
import com.example.kon3050.lastfm.ui.home.MainActivity
import dagger.Binds
import dagger.Module

@Module(includes = arrayOf(BaseActivityModule::class))
abstract class MainActivityModule {

    @ActivityScope
    @Binds
    internal abstract fun baseActivity(mainActivity: MainActivity): BaseActivity

//    @FragmentScope
//    @ContributesAndroidInjector
//    internal abstract fun contributeTopSongsFragment(): TopSongsFragment

}