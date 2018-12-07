package com.example.kon3050.lastfm.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.kon3050.lastfm.ui.navigation.Navigator
import com.example.kon3050.lastfm.ui.navigation.OnBackPressListener
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        //FIXME: Remove when AppInjector is used to inject any android component ( For Example Activities and Fragments).
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        if (navigator.getNavigator().getCurrentFragment() != null && navigator.getNavigator().getCurrentFragment() is OnBackPressListener) {
            (navigator.getNavigator().getCurrentFragment() as OnBackPressListener).onBackPressed()
        } else {
            super.onBackPressed()
            navigator.getNavigator().popStack()
        }
    }
}
