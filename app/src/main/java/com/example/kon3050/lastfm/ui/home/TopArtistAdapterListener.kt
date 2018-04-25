package com.example.kon3050.lastfm.ui.home

import android.view.View

interface TopArtistAdapterListener<T> {
    fun onItemClick(view: View, position: Int, item: T)
}
