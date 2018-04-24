package com.example.kon3050.lastfm.di.modules

import android.content.Context
import android.support.v4.app.FragmentManager
import com.example.kon3050.lastfm.di.scopes.ActivityScope
import com.example.kon3050.lastfm.ui.navigation.Navigator
import com.example.kon3050.lastfm.ui.navigation.NavigatorImpl
import com.example.kon3050.lastfm.ui.navigation.NavigatorManager
import dagger.Module
import dagger.Provides

@Module
class NavigationModule {

    @ActivityScope
    @Provides
    internal fun providesNavigatorManager(fragmentManager: FragmentManager): NavigatorManager {
        return NavigatorManager(fragmentManager)
    }

    @ActivityScope
    @Provides
    internal fun providesNavigator(navigatorManager: NavigatorManager, context: Context): Navigator {
        return NavigatorImpl(navigatorManager, context)
    }

}