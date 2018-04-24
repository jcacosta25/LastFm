package com.example.kon3050.lastfm.ui.detail

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.kon3050.lastfm.R
import com.example.kon3050.lastfm.databinding.ActivityDetailBinding
import com.example.kon3050.lastfm.ui.base.BaseActivity
import dagger.android.AndroidInjection

class DetailActivity :BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityDetailBinding>(this,R.layout.activity_detail)
        navigator.navigateToDetailScreen(intent.getStringExtra(BUNDLE_ARTIST_NAME))
    }
}
