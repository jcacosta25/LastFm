package com.example.kon3050.lastfm.ui.views

import com.example.kon3050.lastfm.ui.base.BaseView
import com.example.kon3050.lastfm.ui.model.ArtistUiModel

interface ArtistView : BaseView {

    fun onLoadArtistSuccessfull(model: ArtistUiModel)
}