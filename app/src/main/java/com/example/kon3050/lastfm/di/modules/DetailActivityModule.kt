package com.example.kon3050.lastfm.di.modules

import com.example.kon3050.lastfm.di.scopes.ActivityScope
import com.example.kon3050.lastfm.ui.base.BaseActivity
import com.example.kon3050.lastfm.ui.detail.DetailActivity
import dagger.Binds
import dagger.Module

@Module(includes = arrayOf(BaseActivityModule::class))
abstract class DetailActivityModule {
    @ActivityScope
    @Binds
    internal abstract fun baseActivity(detailActivity : DetailActivity) : BaseActivity
}