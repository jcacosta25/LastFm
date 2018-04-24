package com.example.kon3050.lastfm.ui.mapper

import com.example.kon3050.lastfm.data.domain.model.ArtistModel
import com.example.kon3050.lastfm.data.domain.model.ListTopArtistModel
import com.example.kon3050.lastfm.data.domain.model.TopArtistModel
import com.example.kon3050.lastfm.ui.model.ArtistUiModel
import com.example.kon3050.lastfm.ui.model.ListTopArtistUiModel
import com.example.kon3050.lastfm.ui.model.TopArtistUiModel
import javax.inject.Inject

class UiMapper @Inject constructor(){

    fun convert(model:ArtistModel) : ArtistUiModel {
        return ArtistUiModel(
                model.artistId,
                model.artistName,
                model.image,
                model.streamable,
                model.onTour,
                model.published,
                model.summary,
                model.content
        )
    }

    fun convert(model:TopArtistModel) : TopArtistUiModel {
        return TopArtistUiModel(
                model.artistId,
                model.artistName,
                model.streamable,
                model.artistListeners,
                model.image
        )
    }

    fun convert(model:ListTopArtistModel, errorMessage:String? = null): ListTopArtistUiModel {
        val uiModel = ListTopArtistUiModel()
        if(model.error || errorMessage != null) {
            uiModel.setError(model.message)
        }
        uiModel.artists.addAll(model.artists.map { convert(it) })
        return uiModel
    }
}