package com.example.kon3050.lastfm.ui.views

import com.example.kon3050.lastfm.ui.model.TopArtistUiModel

interface TopArtistView : BaseView {

    fun onSuccessArtistsLoaded(artistModel: List<TopArtistUiModel>)
}