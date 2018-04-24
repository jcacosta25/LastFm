package com.example.kon3050.lastfm.di.modules

import com.example.kon3050.lastfm.di.scopes.ActivityScope
import com.example.kon3050.lastfm.ui.base.BaseActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScope
@Subcomponent(modules = arrayOf(NavigationModule::class))
interface ActivitySubComponent : AndroidInjector<BaseActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<BaseActivity>() {
//
//        @BindsInstance
//        abstract fun fragmentManager(fragmentManager: FragmentManager): Builder
    }


//    @Subcomponent.Builder
//    interface Builder : AndroidInjector.Builder<YourActivity> {
//
//        @BindsInstance
//        fun fragmentManager(fragmentManager: FragmentManager): Builder
//
//        fun build(): ActivitySubComponent
//    }

}