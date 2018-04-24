package com.example.kon3050.lastfm.ui.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.kon3050.lastfm.R
import com.example.kon3050.lastfm.databinding.ActivityMainBinding
import com.example.kon3050.lastfm.ui.base.BaseActivity
import dagger.android.AndroidInjection

class MainActivity :BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        navigator.navigateToTopArtistScreen()
    }
}
