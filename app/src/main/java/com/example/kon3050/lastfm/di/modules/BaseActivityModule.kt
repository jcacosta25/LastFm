package com.example.kon3050.lastfm.di.modules

import android.support.v4.app.FragmentManager
import com.example.kon3050.lastfm.di.scopes.ActivityScope
import com.example.kon3050.lastfm.ui.base.BaseActivity
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(NavigationModule::class))
class BaseActivityModule {


    @Provides
    @ActivityScope
    internal fun activityFragmentManager(activity: BaseActivity): FragmentManager {
        return activity.supportFragmentManager
    }

//    @ActivityScope
//    @Provides
//    internal fun providesNavigatorManager(fragmentManager: FragmentManager): NavigatorManager {
//        return NavigatorManager(fragmentManager)
//    }
//
//    @ActivityScope
//    @Provides
//    internal fun providesNavigator(navigatorManager: NavigatorManager, context: Context): Navigator {
//        return NavigatorImpl(navigatorManager, context)
//    }
}
