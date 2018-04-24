package com.example.kon3050.lastfm.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.kon3050.lastfm.LastFmApplication
import com.example.kon3050.lastfm.di.ComponentsFactory
import com.example.kon3050.lastfm.di.Injectable
import com.example.kon3050.lastfm.di.components.FragmentComponent
import com.example.kon3050.lastfm.ui.navigation.Navigator

abstract class BaseFragment : Fragment(), Injectable {

    protected lateinit var userComponent: FragmentComponent
    protected lateinit var navigator: Navigator

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userComponent = ComponentsFactory.createUserComponent(LastFmApplication.getAppComponent(context!!))

        if (activity is BaseActivity) {
            navigator = (activity as BaseActivity).navigator
        }

    }
}
